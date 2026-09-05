package com.example

import com.example.data.local.questions.JavaTrickyQuestions
import com.example.data.local.questions.JsTrickyQuestions
import com.example.domain.model.UserDashboard
import com.example.presentation.navigation.ScreenDestination
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TrickyQuestionsAndNavigationTest {

    @Test
    fun testJavaTrickyQuestionsCountAndStructure() {
        val javaQuestions = JavaTrickyQuestions.getAll()
        assertTrue("Java tricky questions must be >= 150, but was ${javaQuestions.size}", javaQuestions.size >= 150)

        // Verify IDs and properties
        val ids = javaQuestions.map { it.id }
        assertEquals("IDs must be unique", ids.size, ids.toSet().size)

        for (q in javaQuestions) {
            assertEquals("java_tricky", q.categoryId)
            assertTrue("Prompt must have code snippet", q.prompt.contains("```java"))
            assertEquals("Must have valid correct answer index", true, q.correctAnswerIndex in 0..3)
            assertTrue("Title must not be empty", q.title.isNotBlank())
            assertTrue("Explanation must not be empty", q.explanation.isNotBlank())
        }
    }

    @Test
    fun testJsTrickyQuestionsCountAndStructure() {
        val jsQuestions = JsTrickyQuestions.getAll()
        assertTrue("JavaScript tricky questions must be >= 150, but was ${jsQuestions.size}", jsQuestions.size >= 150)

        // Verify IDs and properties
        val ids = jsQuestions.map { it.id }
        assertEquals("IDs must be unique", ids.size, ids.toSet().size)

        for (q in jsQuestions) {
            assertEquals("js_tricky", q.categoryId)
            assertTrue("Prompt must have code snippet", q.prompt.contains("```javascript"))
            assertEquals("Must have valid correct answer index", true, q.correctAnswerIndex in 0..3)
            assertTrue("Title must not be empty", q.title.isNotBlank())
            assertTrue("Explanation must not be empty", q.explanation.isNotBlank())
        }
    }

    @Test
    fun testScreenDestinationsIncludeTricky() {
        val destinations = ScreenDestination.entries
        val trickyDestination = destinations.find { it.route == "tricky" }
        assertTrue("ScreenDestination must include TRICKY", trickyDestination != null)
        assertEquals("Tricky", trickyDestination?.label)
        assertEquals("nav_tab_tricky", trickyDestination?.testTag)
        assertTrue("Should have 7 bottom tabs", destinations.size == 7)
    }

    @Test
    fun testUserDashboardIncludesTrickyProgress() {
        val dashboard = UserDashboard(
            readinessScore = 50,
            readinessLevel = "Intermediate Ready",
            questionsCompleted = 10,
            targetQuestions = 300,
            currentStreakDays = 3,
            accuracyPercentage = 75,
            weakAreas = emptyList(),
            todayTrainings = emptyList(),
            dsaSolvedCount = 10,
            totalDsaProblems = 313,
            trickySolvedCount = 25,
            trickyTotalCount = 310,
            trickyAccuracy = 80
        )

        assertEquals(25, dashboard.trickySolvedCount)
        assertEquals(310, dashboard.trickyTotalCount)
        assertEquals(80, dashboard.trickyAccuracy)
        val percent = (dashboard.trickySolvedCount.toFloat() / dashboard.trickyTotalCount.toFloat() * 100).toInt()
        assertEquals(8, percent)
    }
}
