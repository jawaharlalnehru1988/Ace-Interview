package com.example

import com.example.data.local.entity.InterviewResponseEntity
import com.example.data.local.entity.QuestionAttemptEntity
import com.example.data.local.entity.QuizSessionEntity
import com.example.domain.model.TrainingType
import com.example.util.training.DailyTrainingScheduler
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DailyTrainingSchedulerTest {

    @Test
    fun testMcqStacksCountIsTen() {
        assertEquals(10, DailyTrainingScheduler.mcqStacks.size)
    }

    @Test
    fun testMcqRotationCyclesThroughDifferentStacksEachDay() {
        val daysToTest = 10
        val observedCategories = mutableListOf<String>()

        for (day in 0 until daysToTest) {
            val training = DailyTrainingScheduler.getTodayMcqTraining(epochDay = day.toLong())
            observedCategories.add(training.category)
            assertEquals(TrainingType.MCQ, training.type)
            // Questions count should be dynamic from catalog, NOT fixed 10
            assertTrue("Questions count should be > 0", training.questionsCount > 0)
        }

        // Each consecutive day in the 10-day cycle must have a unique stack
        assertEquals(10, observedCategories.distinct().size)
    }

    @Test
    fun testMcqCompletionStatus() {
        val epochDay = 100L
        val trainingIncomplete = DailyTrainingScheduler.getTodayMcqTraining(epochDay = epochDay)
        assertFalse(trainingIncomplete.isCompleted)
        assertEquals(0, trainingIncomplete.completedCount)

        val oneDayMs = 24 * 60 * 60 * 1000L
        val todayMs = epochDay * oneDayMs + 1000L

        // When user completed a session for this concept today
        val sessions = listOf(
            QuizSessionEntity(
                categoryId = trainingIncomplete.targetId,
                totalQuestions = trainingIncomplete.questionsCount,
                correctCount = trainingIncomplete.questionsCount,
                scorePercentage = 100,
                startedAt = todayMs - 60000L,
                completedAt = todayMs
            )
        )

        val trainingComplete = DailyTrainingScheduler.getTodayMcqTraining(
            epochDay = epochDay,
            sessions = sessions
        )
        assertTrue(trainingComplete.isCompleted)
        assertEquals(trainingComplete.questionsCount, trainingComplete.completedCount)
    }

    @Test
    fun testInterviewRotationCyclesThroughTracks() {
        val daysToTest = 11
        val observedTracks = mutableListOf<String>()

        for (day in 0 until daysToTest) {
            val training = DailyTrainingScheduler.getTodayInterviewTraining(epochDay = day.toLong())
            observedTracks.add(training.targetId)
            assertEquals(TrainingType.INTERVIEW, training.type)
            assertEquals(5, training.targetGoalCount)
        }

        assertEquals(11, observedTracks.distinct().size)
    }

    @Test
    fun testInterviewCompletionRequiresFiveAudioRecordings() {
        val epochDay = 50L
        val training = DailyTrainingScheduler.getTodayInterviewTraining(epochDay = epochDay)
        assertEquals(5, training.targetGoalCount)

        // Case 1: 0 audio recordings
        val training0 = DailyTrainingScheduler.getTodayInterviewTraining(
            epochDay = epochDay,
            audioResponses = emptyList()
        )
        assertFalse(training0.isCompleted)
        assertEquals(0, training0.completedCount)

        // Case 2: 3 audio recordings (incomplete)
        val audioList3 = (1..3).map { i ->
            InterviewResponseEntity(
                sessionId = 1L,
                trackId = training.targetId,
                questionId = "q_$i",
                questionNumber = i,
                questionText = "Question $i",
                responseText = "",
                aiFeedback = "",
                score = 0,
                audioFilePath = "/path/audio_$i.m4a",
                audioDurationMs = 15000L,
                conceptName = training.targetConceptName ?: "",
                shortAnswer = "",
                recordedAt = System.currentTimeMillis()
            )
        }
        val training3 = DailyTrainingScheduler.getTodayInterviewTraining(
            epochDay = epochDay,
            audioResponses = audioList3
        )
        assertFalse(training3.isCompleted)
        assertEquals(3, training3.completedCount)

        // Case 3: 5 audio recordings (completes!)
        val audioList5 = (1..5).map { i ->
            InterviewResponseEntity(
                sessionId = 1L,
                trackId = training.targetId,
                questionId = "q_$i",
                questionNumber = i,
                questionText = "Question $i",
                responseText = "",
                aiFeedback = "",
                score = 0,
                audioFilePath = "/path/audio_$i.m4a",
                audioDurationMs = 15000L,
                conceptName = training.targetConceptName ?: "",
                shortAnswer = "",
                recordedAt = System.currentTimeMillis()
            )
        }
        val training5 = DailyTrainingScheduler.getTodayInterviewTraining(
            epochDay = epochDay,
            audioResponses = audioList5
        )
        assertTrue(training5.isCompleted)
        assertEquals(5, training5.completedCount)
    }
}
