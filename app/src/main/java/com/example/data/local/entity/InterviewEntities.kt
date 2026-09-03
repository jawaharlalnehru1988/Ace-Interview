package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Question
import org.json.JSONArray

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey val id: String,
    val categoryId: String,
    val title: String,
    val prompt: String,
    val optionsJson: String,
    val correctAnswerIndex: Int,
    val explanation: String,
    val difficulty: String,
    val tags: String
)

fun QuestionEntity.toDomain(): Question {
    val optionsList = try {
        val raw = optionsJson.trim()
        if (raw.startsWith("[") && raw.endsWith("]")) {
            val array = JSONArray(raw)
            List(array.length()) { array.getString(it) }
        } else {
            raw.split("||").map { it.trim() }.filter { it.isNotEmpty() }
        }
    } catch (e: Exception) {
        optionsJson.split("||").map { it.trim() }.filter { it.isNotEmpty() }
    }
    val tagsList = tags.split(",").map { it.trim() }.filter { it.isNotEmpty() }
    return Question(
        id = id,
        categoryId = categoryId,
        title = title,
        prompt = prompt,
        options = optionsList,
        correctAnswerIndex = correctAnswerIndex,
        explanation = explanation,
        difficulty = difficulty,
        tags = tagsList
    )
}

fun Question.toEntity(): QuestionEntity {
    val jsonArray = JSONArray()
    options.forEach { jsonArray.put(it) }
    return QuestionEntity(
        id = id,
        categoryId = categoryId,
        title = title,
        prompt = prompt,
        optionsJson = jsonArray.toString(),
        correctAnswerIndex = correctAnswerIndex,
        explanation = explanation,
        difficulty = difficulty,
        tags = tags.joinToString(",")
    )
}

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val totalQuestions: Int,
    val iconName: String
)

@Entity(tableName = "question_attempts")
data class QuestionAttemptEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val questionId: String,
    val selectedOptionIndex: Int,
    val isCorrect: Boolean,
    val timeSpentSeconds: Int,
    val timestamp: Long
)

@Entity(tableName = "quiz_sessions")
data class QuizSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val categoryId: String,
    val totalQuestions: Int,
    val correctCount: Int,
    val scorePercentage: Int,
    val startedAt: Long,
    val completedAt: Long
)

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val id: Int = 1,
    val readinessScore: Int,
    val questionsCompleted: Int,
    val totalQuestionsTarget: Int,
    val currentStreakDays: Int,
    val accuracyRate: Float,
    val dsaSolvedCount: Int,
    val interviewSessionsCount: Int,
    val lastActiveTimestamp: Long
)

@Entity(tableName = "dsa_problems")
data class DsaProblemEntity(
    @PrimaryKey val id: String,
    val topic: String,
    val title: String,
    val difficulty: String,
    val description: String,
    val leetcodeUrl: String
)

@Entity(tableName = "dsa_attempts")
data class DsaAttemptEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val problemId: String,
    val status: String,
    val language: String,
    val notes: String,
    val attemptedAt: Long
)

@Entity(tableName = "interview_sessions")
data class InterviewSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val trackTitle: String,
    val roleLevel: String,
    val questionsCount: Int,
    val durationMinutes: Int,
    val status: String,
    val completedAt: Long,
    val overallScore: Int
)

@Entity(tableName = "interview_responses")
data class InterviewResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sessionId: Long,
    val questionNumber: Int,
    val questionText: String,
    val responseText: String,
    val aiFeedback: String,
    val score: Int
)
