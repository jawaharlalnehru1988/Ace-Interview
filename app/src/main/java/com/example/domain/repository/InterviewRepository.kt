package com.example.domain.repository

import com.example.domain.model.DsaProblem
import com.example.domain.model.DsaTopic
import com.example.domain.model.InterviewTrack
import com.example.domain.model.Question
import com.example.domain.model.TechnicalCategory
import com.example.domain.model.UserDashboard
import com.example.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface InterviewRepository {
    fun getUserDashboard(): Flow<UserDashboard>
    fun getTechnicalCategories(): Flow<List<TechnicalCategory>>
    fun getDsaTopics(): Flow<List<DsaTopic>>
    fun getInterviewTracks(): Flow<List<InterviewTrack>>
    fun getUserProfile(): Flow<UserProfile>

    // DSA problems
    fun getDsaProblems(topicId: String): Flow<List<DsaProblem>>
    suspend fun toggleDsaProblemSolved(problemId: String)

    // Question bank & Room integration
    fun getAllQuestions(): Flow<List<Question>>
    fun getQuestionsByCategory(categoryId: String): Flow<List<Question>>
    suspend fun getQuestionById(id: String): Question?
    suspend fun seedSampleQuestions()
    suspend fun recordQuestionAttempt(questionId: String, selectedIndex: Int, isCorrect: Boolean, timeSpentSeconds: Int)
    suspend fun recordQuizSession(categoryId: String, totalQuestions: Int, correctCount: Int, scorePercentage: Int)
}
