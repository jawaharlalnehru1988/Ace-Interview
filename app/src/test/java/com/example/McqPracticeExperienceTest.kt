package com.example

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.database.AceInterviewDatabase
import com.example.data.local.database.SampleQuestionData
import com.example.data.repository.InterviewRepositoryImpl
import com.example.presentation.practice.McqPracticeViewModel
import com.example.presentation.practice.McqSessionState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class McqPracticeExperienceTest {

    private lateinit var database: AceInterviewDatabase
    private lateinit var repository: InterviewRepositoryImpl
    private lateinit var viewModel: McqPracticeViewModel

    @Before
    fun setup() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AceInterviewDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        database.questionDao().insertQuestions(SampleQuestionData.sampleQuestions)
        repository = InterviewRepositoryImpl(database)
        viewModel = McqPracticeViewModel(repository)
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun testJavaTracksHave100QuestionsEach() = runBlocking {
        // Test Java Beginner
        viewModel.startQuiz("java_beginner", "Java Beginner")
        val begState = viewModel.sessionState.first { it is McqSessionState.Active } as McqSessionState.Active
        assertEquals(100, begState.totalQuestions)

        // Test Java Intermediate
        viewModel.startQuiz("java_intermediate", "Java Intermediate")
        val intState = viewModel.sessionState.first { it is McqSessionState.Active } as McqSessionState.Active
        assertEquals(100, intState.totalQuestions)

        // Test Java Advanced
        viewModel.startQuiz("java_advanced", "Java Advanced")
        val advState = viewModel.sessionState.first { it is McqSessionState.Active } as McqSessionState.Active
        assertEquals(100, advState.totalQuestions)

        // Test Complete Java Track
        viewModel.startQuiz("java", "Java Complete")
        val allState = viewModel.sessionState.first { it is McqSessionState.Active } as McqSessionState.Active
        assertEquals(300, allState.totalQuestions)
    }

    @Test
    fun testCompleteMcqQuizFlow() = runBlocking {
        // 1. Start Spring Boot quiz (2 questions for fast end-to-end completion)
        viewModel.startQuiz("spring_boot", "Spring Boot Framework")

        var state = viewModel.sessionState.first { it is McqSessionState.Active } as McqSessionState.Active
        assertEquals("spring_boot", state.categoryId)
        assertEquals("Spring Boot Framework", state.categoryName)
        assertEquals(2, state.totalQuestions)
        assertEquals(0, state.currentIndex)
        assertEquals(null, state.selectedOptionIndex)
        assertEquals(false, state.isSubmitted)

        val q1 = state.currentQuestion
        assertNotNull(q1.title)
        assertTrue(q1.options.isNotEmpty())

        // 2. Select option and submit
        val correctAns = q1.correctAnswerIndex
        viewModel.selectOption(correctAns)
        state = viewModel.sessionState.value as McqSessionState.Active
        assertEquals(correctAns, state.selectedOptionIndex)

        viewModel.submitAnswer()
        state = viewModel.sessionState.value as McqSessionState.Active
        assertEquals(true, state.isSubmitted)
        assertEquals(true, state.isCorrect)
        assertEquals(true, state.explanationVisible)
        assertEquals(1, state.results.size)

        // 3. Move to next question
        viewModel.nextQuestion()
        state = viewModel.sessionState.value as McqSessionState.Active
        assertEquals(1, state.currentIndex)
        assertEquals(null, state.selectedOptionIndex)
        assertEquals(false, state.isSubmitted)

        // 4. Select wrong option and submit
        val q2 = state.currentQuestion
        val wrongAns = (q2.correctAnswerIndex + 1) % q2.options.size
        viewModel.selectOption(wrongAns)
        viewModel.submitAnswer()
        state = viewModel.sessionState.value as McqSessionState.Active
        assertEquals(true, state.isSubmitted)
        assertEquals(false, state.isCorrect)
        assertEquals(2, state.results.size)

        // 5. Complete Quiz -> Finished Summary State
        viewModel.nextQuestion()
        val finishedState = viewModel.sessionState.first { it is McqSessionState.Finished } as McqSessionState.Finished
        assertEquals("spring_boot", finishedState.categoryId)
        assertEquals(2, finishedState.summary.totalQuestions)
        assertEquals(1, finishedState.summary.correctCount)
        assertEquals(1, finishedState.summary.incorrectCount)
        assertEquals(50, finishedState.summary.scorePercentage)
        assertEquals(2, finishedState.summary.questionResults.size)

        // Verify Room persistence
        val attempts = database.quizDao().getAllAttempts().first()
        assertTrue("Expected recorded attempts in Room", attempts.size >= 2)

        val sessions = database.quizDao().getAllSessions().first()
        assertTrue("Expected recorded session in Room", sessions.isNotEmpty())
        assertEquals(50, sessions.first().scorePercentage)
    }
}
