package com.example.presentation.practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Question
import com.example.domain.model.QuestionResult
import com.example.domain.model.QuizSummary
import com.example.domain.repository.InterviewRepository
import com.example.domain.model.TechnicalConceptCatalog
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
            } else {
                val concept = TechnicalConceptCatalog.findConcept(categoryId)
                if (concept != null) {
                    val domainCategory = TechnicalConceptCatalog.getDomainForConcept(categoryId)
                    val domainQuestions = repository.getQuestionsByCategory(domainCategory).first()
                    val filtered = domainQuestions.filter { q ->
                        TechnicalConceptCatalog.matchesConcept(concept, q.title, q.prompt, q.tags)
                    }
                    if (filtered.isNotEmpty()) filtered else domainQuestions
                } else if (categoryId == "java_tricky" || categoryId == "js_tricky") {
                    repository.getQuestionsByCategory(categoryId).first()
                } else if (categoryId.startsWith("java_") || categoryId.startsWith("spring_") || categoryId.startsWith("ms_") || categoryId.startsWith("hld_") || categoryId.startsWith("lld_") || categoryId.startsWith("sql_") || categoryId.startsWith("ng_") || categoryId.startsWith("sec_") || categoryId.startsWith("sys_") || categoryId.startsWith("devops_")) {
                    val targetDifficulty = when {
                        categoryId.contains("beginner", ignoreCase = true) -> "Beginner"
                        categoryId.contains("intermediate", ignoreCase = true) -> "Intermediate"
                        categoryId.contains("advanced", ignoreCase = true) -> "Advanced"
                        else -> null
                    }
                    val domainCategory = when {
                        categoryId.startsWith("java_") -> "java"
                        categoryId.startsWith("spring_") -> "spring_boot"
                        categoryId.startsWith("ms_") -> "microservices"
                        categoryId.startsWith("hld_") -> "hld"
                        categoryId.startsWith("lld_") -> "lld"
                        categoryId.startsWith("sql_") -> "sql"
                        categoryId.startsWith("ng_") -> "angular"
                        categoryId.startsWith("sys_") -> "system_design"
                        categoryId.startsWith("devops_") -> "devops"
                        else -> "security"
                    }
                    val domainQuestions = repository.getQuestionsByCategory(domainCategory).first()
                    if (targetDifficulty != null) {
                        val filtered = domainQuestions.filter { it.difficulty.equals(targetDifficulty, ignoreCase = true) }
                        if (filtered.isNotEmpty()) filtered else domainQuestions
                    } else {
                        domainQuestions
                    }
                } else {
                    val categoryQuestions = repository.getQuestionsByCategory(categoryId).first()
                    if (categoryQuestions.isEmpty()) {
                        repository.getAllQuestions().first()
                    } else {
                        categoryQuestions
                    }
                }
            }

            val difficultyRank = mapOf("beginner" to 1, "intermediate" to 2, "advanced" to 3)
            val sortedQuestions = questions.sortedWith(
                compareBy(
                    { difficultyRank[it.difficulty.lowercase()] ?: 4 },
                    { it.id }
                )
            )

            if (sortedQuestions.isEmpty()) {
                _sessionState.value = McqSessionState.Empty
            } else {
                _sessionState.value = McqSessionState.Active(
                    categoryId = categoryId,
                    categoryName = categoryName,
                    questions = sortedQuestions,
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
            repository.setLastAttemptedConcept(activeState.categoryId)
        }

        _sessionState.value = McqSessionState.Finished(
            categoryId = activeState.categoryId,
            categoryName = activeState.categoryName,
            summary = summary
        )
    }

    fun finishAndReturn(onReturn: () -> Unit) {
        val current = _sessionState.value
        if (current is McqSessionState.Active) {
            val total = current.results.size
            val correct = current.results.count { it.isCorrect }
            val percentage = if (total > 0) ((correct.toFloat() / total) * 100).toInt() else 0

            viewModelScope.launch {
                repository.recordQuizSession(
                    categoryId = current.categoryId,
                    totalQuestions = total,
                    correctCount = correct,
                    scorePercentage = percentage
                )
                repository.setLastAttemptedConcept(current.categoryId)
                onReturn()
            }
        } else if (current is McqSessionState.Finished) {
            repository.setLastAttemptedConcept(current.categoryId)
            onReturn()
        } else {
            onReturn()
        }
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
