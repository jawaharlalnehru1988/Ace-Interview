package com.example.presentation.practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Question
import com.example.domain.model.QuestionResult
import com.example.domain.model.QuizSummary
import com.example.domain.repository.InterviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface McqSessionState {
    data object Loading : McqSessionState
    data object Empty : McqSessionState
    data class Active(
        val categoryId: String,
        val categoryName: String,
        val questions: List<Question>,
        val currentIndex: Int,
        val selectedOptionIndex: Int? = null,
        val isSubmitted: Boolean = false,
        val isCorrect: Boolean? = null,
        val explanationVisible: Boolean = false,
        val results: List<QuestionResult> = emptyList(),
        val questionStartTimeMs: Long = System.currentTimeMillis()
    ) : McqSessionState {
        val currentQuestion: Question
            get() = questions[currentIndex]

        val totalQuestions: Int
            get() = questions.size

        val isLastQuestion: Boolean
            get() = currentIndex == questions.size - 1

        val progressFraction: Float
            get() = if (questions.isEmpty()) 0f else (currentIndex + 1).toFloat() / questions.size
    }

    data class Finished(
        val categoryId: String,
        val categoryName: String,
        val summary: QuizSummary
    ) : McqSessionState
}

class McqPracticeViewModel(
    private val repository: InterviewRepository
) : ViewModel() {

    private val _sessionState = MutableStateFlow<McqSessionState>(McqSessionState.Loading)
    val sessionState: StateFlow<McqSessionState> = _sessionState.asStateFlow()

    fun startQuiz(categoryId: String, categoryName: String) {
        viewModelScope.launch {
            _sessionState.value = McqSessionState.Loading
            val questions = if (categoryId == "all" || categoryId.isBlank()) {
                repository.getAllQuestions().first()
            } else if (categoryId.startsWith("java_")) {
                val targetDifficulty = when {
                    categoryId.contains("beginner", ignoreCase = true) -> "Beginner"
                    categoryId.contains("intermediate", ignoreCase = true) -> "Intermediate"
                    categoryId.contains("advanced", ignoreCase = true) -> "Advanced"
                    else -> null
                }
                val javaQuestions = repository.getQuestionsByCategory("java").first()
                if (targetDifficulty != null) {
                    val filtered = javaQuestions.filter { it.difficulty.equals(targetDifficulty, ignoreCase = true) }
                    if (filtered.isNotEmpty()) filtered else javaQuestions
                } else {
                    javaQuestions
                }
            } else {
                val categoryQuestions = repository.getQuestionsByCategory(categoryId).first()
                if (categoryQuestions.isEmpty()) {
                    // Fall back to all questions if specific category has no questions yet
                    repository.getAllQuestions().first()
                } else {
                    categoryQuestions
                }
            }

            if (questions.isEmpty()) {
                _sessionState.value = McqSessionState.Empty
            } else {
                _sessionState.value = McqSessionState.Active(
                    categoryId = categoryId,
                    categoryName = categoryName,
                    questions = questions,
                    currentIndex = 0,
                    selectedOptionIndex = null,
                    isSubmitted = false,
                    isCorrect = null,
                    explanationVisible = false,
                    results = emptyList(),
                    questionStartTimeMs = System.currentTimeMillis()
                )
            }
        }
    }

    fun selectOption(optionIndex: Int) {
        _sessionState.update { current ->
            if (current is McqSessionState.Active && !current.isSubmitted) {
                current.copy(selectedOptionIndex = optionIndex)
            } else {
                current
            }
        }
    }

    fun submitAnswer() {
        val current = _sessionState.value
        if (current is McqSessionState.Active && current.selectedOptionIndex != null && !current.isSubmitted) {
            val selected = current.selectedOptionIndex
            val question = current.currentQuestion
            val isCorrect = (selected == question.correctAnswerIndex)
            val timeSpentSec = ((System.currentTimeMillis() - current.questionStartTimeMs) / 1000).toInt().coerceAtLeast(1)

            val newResult = QuestionResult(
                question = question,
                selectedOptionIndex = selected,
                isCorrect = isCorrect
            )
            val updatedResults = current.results + newResult

            // Record to Room asynchronously
            viewModelScope.launch {
                repository.recordQuestionAttempt(
                    questionId = question.id,
                    selectedIndex = selected,
                    isCorrect = isCorrect,
                    timeSpentSeconds = timeSpentSec
                )
            }

            _sessionState.value = current.copy(
                isSubmitted = true,
                isCorrect = isCorrect,
                explanationVisible = true,
                results = updatedResults
            )
        }
    }

    fun nextQuestion() {
        val current = _sessionState.value
        if (current is McqSessionState.Active && current.isSubmitted) {
            if (current.isLastQuestion) {
                // Finish session
                finishQuiz(current)
            } else {
                _sessionState.value = current.copy(
                    currentIndex = current.currentIndex + 1,
                    selectedOptionIndex = null,
                    isSubmitted = false,
                    isCorrect = null,
                    explanationVisible = false,
                    questionStartTimeMs = System.currentTimeMillis()
                )
            }
        }
    }

    private fun finishQuiz(activeState: McqSessionState.Active) {
        val total = activeState.results.size
        val correct = activeState.results.count { it.isCorrect }
        val incorrect = total - correct
        val percentage = if (total > 0) ((correct.toFloat() / total) * 100).toInt() else 0

        val summary = QuizSummary(
            totalQuestions = total,
            correctCount = correct,
            incorrectCount = incorrect,
            scorePercentage = percentage,
            questionResults = activeState.results
        )

        // Record completed session to Room
        viewModelScope.launch {
            repository.recordQuizSession(
                categoryId = activeState.categoryId,
                totalQuestions = total,
                correctCount = correct,
                scorePercentage = percentage
            )
        }

        _sessionState.value = McqSessionState.Finished(
            categoryId = activeState.categoryId,
            categoryName = activeState.categoryName,
            summary = summary
        )
    }

    fun restartQuiz() {
        val current = _sessionState.value
        when (current) {
            is McqSessionState.Finished -> {
                startQuiz(current.categoryId, current.categoryName)
            }
            is McqSessionState.Active -> {
                startQuiz(current.categoryId, current.categoryName)
            }
            else -> {}
        }
    }
}
