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
    fun getLastAttemptedConceptId(): Flow<String?>
    fun setLastAttemptedConcept(conceptId: String)

    // Interview Mock Sessions & Audio Answers
    fun getInterviewQuestionsForTrack(trackId: String): Flow<List<com.example.domain.model.InterviewQuestion>>
    fun getConceptGroupsForTrack(trackId: String): Flow<List<com.example.domain.model.ConceptInterviewGroup>>
    fun getAudioAnswersForTrack(trackId: String): Flow<Map<String, com.example.domain.model.QuestionAudioAnswer>>
    suspend fun saveAudioAnswer(
        questionId: String,
        trackId: String,
        conceptName: String,
        questionText: String,
        shortAnswer: String,
        audioFilePath: String,
        durationMs: Long
    )
    suspend fun deleteAudioAnswer(questionId: String)
}
