package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.CategoryEntity
import com.example.data.local.entity.DsaAttemptEntity
import com.example.data.local.entity.DsaProblemEntity
import com.example.data.local.entity.InterviewResponseEntity
import com.example.data.local.entity.InterviewSessionEntity
import com.example.data.local.entity.QuestionAttemptEntity
import com.example.data.local.entity.QuestionEntity
import com.example.data.local.entity.QuizSessionEntity
import com.example.data.local.entity.UserProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Query("SELECT COUNT(*) FROM categories")
    suspend fun getCategoryCount(): Int
}

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions ORDER BY id ASC")
    fun getAllQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE categoryId = :categoryId ORDER BY id ASC")
    fun getQuestionsByCategory(categoryId: String): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE id = :id")
    suspend fun getQuestionById(id: String): QuestionEntity?

    @Query("SELECT COUNT(*) FROM questions")
    fun getTotalQuestionCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun getQuestionCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity)

    @Query("DELETE FROM questions WHERE id = :id")
    suspend fun deleteQuestionById(id: String)

    @Query("DELETE FROM questions")
    suspend fun clearAllQuestions()
}

@Dao
interface QuizDao {
    @Query("SELECT * FROM question_attempts ORDER BY timestamp DESC")
    fun getAllAttempts(): Flow<List<QuestionAttemptEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttempt(attempt: QuestionAttemptEntity): Long

    @Query("SELECT * FROM quiz_sessions ORDER BY completedAt DESC")
    fun getAllSessions(): Flow<List<QuizSessionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: QuizSessionEntity): Long
}

@Dao
interface UserProgressDao {
    @Query("SELECT * FROM user_progress WHERE id = 1")
    fun getUserProgress(): Flow<UserProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProgress(progress: UserProgressEntity)
}

@Dao
interface DsaDao {
    @Query("SELECT * FROM dsa_problems")
    fun getAllProblems(): Flow<List<DsaProblemEntity>>

    @Query("SELECT * FROM dsa_problems WHERE topic = :topic")
    fun getProblemsByTopic(topic: String): Flow<List<DsaProblemEntity>>

    @Query("SELECT * FROM dsa_attempts ORDER BY attemptedAt DESC")
    fun getAllAttempts(): Flow<List<DsaAttemptEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProblem(problem: DsaProblemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttempt(attempt: DsaAttemptEntity): Long
}

@Dao
interface InterviewDao {
    @Query("SELECT * FROM interview_sessions ORDER BY completedAt DESC")
    fun getAllSessions(): Flow<List<InterviewSessionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: InterviewSessionEntity): Long

    @Query("SELECT * FROM interview_responses WHERE sessionId = :sessionId ORDER BY questionNumber ASC")
    fun getResponsesForSession(sessionId: Long): Flow<List<InterviewResponseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResponse(response: InterviewResponseEntity): Long
}
