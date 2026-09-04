package com.example.domain.model

data class UserDashboard(
    val readinessScore: Int,
    val readinessLevel: String,
    val questionsCompleted: Int,
    val targetQuestions: Int,
    val currentStreakDays: Int,
    val accuracyPercentage: Int,
    val weakAreas: List<WeakArea>,
    val todayTrainings: List<TodayTraining>
)

data class WeakArea(
    val id: String,
    val topic: String,
    val subtopic: String,
    val accuracy: Int,
    val recommendation: String
)

data class TodayTraining(
    val id: String,
    val title: String,
    val category: String,
    val questionsCount: Int,
    val estimatedMinutes: Int,
    val isCompleted: Boolean
)

data class TechnicalCategory(
    val id: String,
    val name: String,
    val description: String,
    val questionCount: Int,
    val difficulty: String,
    val badgeText: String,
    val concepts: List<TechnicalConceptModule> = emptyList()
)

data class DsaTopic(
    val id: String,
    val name: String,
    val description: String,
    val problemsCount: Int,
    val solvedCount: Int,
    val easyCount: Int,
    val mediumCount: Int,
    val hardCount: Int
)

data class DsaProblem(
    val id: String,
    val topic: String,
    val title: String,
    val difficulty: String,
    val pattern: String,
    val timeComplexity: String,
    val spaceComplexity: String,
    val description: String,
    val exampleInput: String,
    val exampleOutput: String,
    val keyInsight: String,
    val solutionCode: String,
    val isSolved: Boolean = false
)

data class InterviewTrack(
    val id: String,
    val title: String,
    val roleLevel: String,
    val durationMinutes: Int,
    val questionCount: Int,
    val format: String,
    val description: String
)

data class UserProfile(
    val name: String,
    val targetRole: String,
    val targetTimeline: String,
    val overallLevel: String,
    val questionsAttempted: Int,
    val accuracyPercentage: Int,
    val dsaProblemsSolved: Int,
    val interviewSessions: Int,
    val streakDays: Int
)

data class Question(
    val id: String,
    val categoryId: String,
    val title: String,
    val prompt: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String,
    val difficulty: String,
    val tags: List<String> = emptyList()
)

data class QuestionResult(
    val question: Question,
    val selectedOptionIndex: Int,
    val isCorrect: Boolean
)

data class QuizSummary(
    val totalQuestions: Int,
    val correctCount: Int,
    val incorrectCount: Int,
    val scorePercentage: Int,
    val questionResults: List<QuestionResult>
)
