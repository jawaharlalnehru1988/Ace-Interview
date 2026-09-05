package com.example

import com.example.data.local.functional.FunctionalProblemCatalog
import com.example.data.local.functional.JavaStreamProblems
import com.example.data.local.functional.JsFunctionalProblems
import com.example.data.local.functional.RxjsProblems
import com.example.presentation.navigation.ScreenDestination
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FunctionalProgrammingCatalogAndNavigationTest {

    @Test
    fun testJavaStreamProblemsCountAndStructure() {
        val javaProblems = JavaStreamProblems.getAll()
        assertTrue("Java stream problems must be >= 100, but was ${javaProblems.size}", javaProblems.size >= 100)

        val ids = javaProblems.map { it.id }
        assertEquals("Java stream problem IDs must be unique", ids.size, ids.toSet().size)

        for (p in javaProblems) {
            assertEquals("java_stream", p.trackId)
            assertTrue("ID must start with stream_", p.id.startsWith("stream_"))
            assertTrue("Title must not be blank", p.title.isNotBlank())
            assertTrue("Category must not be blank", p.category.isNotBlank())
            assertTrue("Pattern must not be blank", p.pattern.isNotBlank())
            assertTrue("Description must not be blank", p.description.isNotBlank())
            assertTrue("Input data must not be blank", p.inputData.isNotBlank())
            assertTrue("Expected output must not be blank", p.expectedOutput.isNotBlank())
            assertTrue("Functional solution must not be blank", p.functionalSolution.isNotBlank())
            assertTrue("Imperative solution must not be blank", p.imperativeSolution.isNotBlank())
            assertTrue("Comparison insight must not be blank", p.comparisonInsight.isNotBlank())
        }
    }

    @Test
    fun testJsFunctionalProblemsCountAndStructure() {
        val jsProblems = JsFunctionalProblems.getAll()
        assertTrue("JS functional problems must be >= 100, but was ${jsProblems.size}", jsProblems.size >= 100)

        val ids = jsProblems.map { it.id }
        assertEquals("JS functional problem IDs must be unique", ids.size, ids.toSet().size)

        for (p in jsProblems) {
            assertEquals("js_functional", p.trackId)
            assertTrue("ID must start with js_func_", p.id.startsWith("js_func_"))
            assertTrue("Title must not be blank", p.title.isNotBlank())
            assertTrue("Category must not be blank", p.category.isNotBlank())
            assertTrue("Pattern must not be blank", p.pattern.isNotBlank())
            assertTrue("Description must not be blank", p.description.isNotBlank())
            assertTrue("Input data must not be blank", p.inputData.isNotBlank())
            assertTrue("Expected output must not be blank", p.expectedOutput.isNotBlank())
            assertTrue("Functional solution must not be blank", p.functionalSolution.isNotBlank())
            assertTrue("Imperative solution must not be blank", p.imperativeSolution.isNotBlank())
            assertTrue("Comparison insight must not be blank", p.comparisonInsight.isNotBlank())
        }
    }

    @Test
    fun testRxjsProblemsCountAndStructure() {
        val rxjsProblems = RxjsProblems.getAll()
        assertTrue("RxJS problems must be >= 100, but was ${rxjsProblems.size}", rxjsProblems.size >= 100)

        val ids = rxjsProblems.map { it.id }
        assertEquals("RxJS problem IDs must be unique", ids.size, ids.toSet().size)

        for (p in rxjsProblems) {
            assertEquals("rxjs", p.trackId)
            assertTrue("ID must start with rxjs_", p.id.startsWith("rxjs_"))
            assertTrue("Title must not be blank", p.title.isNotBlank())
            assertTrue("Category must not be blank", p.category.isNotBlank())
            assertTrue("Pattern must not be blank", p.pattern.isNotBlank())
            assertTrue("Description must not be blank", p.description.isNotBlank())
            assertTrue("Input data must not be blank", p.inputData.isNotBlank())
            assertTrue("Expected output must not be blank", p.expectedOutput.isNotBlank())
            assertTrue("Functional solution must not be blank", p.functionalSolution.isNotBlank())
            assertTrue("Imperative solution must not be blank", p.imperativeSolution.isNotBlank())
            assertTrue("Comparison insight must not be blank", p.comparisonInsight.isNotBlank())
        }
    }

    @Test
    fun testGlobalUniquenessAcrossAllTracks() {
        val allProblems = FunctionalProblemCatalog.getAllProblems()
        assertTrue("Total functional problems must be >= 300, but was ${allProblems.size}", allProblems.size >= 300)

        val allIds = allProblems.map { it.id }
        assertEquals("All problem IDs across all tracks must be globally unique", allIds.size, allIds.toSet().size)

        val tracks = FunctionalProblemCatalog.getTracks()
        assertEquals(3, tracks.size)
        assertEquals(listOf("java_stream", "js_functional", "rxjs"), tracks.map { it.id })
    }

    @Test
    fun testScreenDestinationsIncludeFunctional() {
        val destinations = ScreenDestination.entries
        val functionalDestination = destinations.find { it.route == "functional" }

        assertTrue("ScreenDestination must include FUNCTIONAL", functionalDestination != null)
        assertEquals("Functional", functionalDestination?.label)
        assertEquals("nav_tab_functional", functionalDestination?.testTag)
        assertEquals(7, destinations.size)
    }
}
