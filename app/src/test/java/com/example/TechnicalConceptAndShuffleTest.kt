package com.example

import com.example.data.local.questions.QuestionHelper
import com.example.domain.model.TechnicalConceptCatalog
import org.json.JSONArray
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class TechnicalConceptAndShuffleTest {

    @Test
    fun testDeterministicOptionShufflingPreservesCorrectAnswer() {
        val qId = "java_test_001"
        val opt0 = "Incorrect Alpha"
        val opt1 = "Correct Answer Content"
        val opt2 = "Incorrect Beta"
        val opt3 = "Incorrect Gamma"
        val originalOptions = listOf(opt0, opt1, opt2, opt3)
        val originalCorrectIndex = 1 // opt1 is "Correct Answer Content"

        val prepared = QuestionHelper.q(
            id = qId,
            categoryId = "java",
            title = "Test Question",
            prompt = "What is the expected behavior?",
            opt0 = opt0,
            opt1 = opt1,
            opt2 = opt2,
            opt3 = opt3,
            correctIndex = originalCorrectIndex,
            explanation = "Explanation",
            difficulty = "Intermediate",
            tags = "java,test"
        )

        val jsonArray = JSONArray(prepared.optionsJson)
        val preparedOptions = (0 until jsonArray.length()).map { jsonArray.getString(it) }

        // Options should contain all 4 original items
        assertEquals(4, preparedOptions.size)
        assertTrue(preparedOptions.containsAll(originalOptions))

        // The option at prepared.correctAnswerIndex must be the original correct answer
        val actualCorrectOption = preparedOptions[prepared.correctAnswerIndex]
        assertEquals("Correct Answer Content", actualCorrectOption)

        // Shuffling must be deterministic: identical ID produces identical option order and correct index
        val preparedAgain = QuestionHelper.q(
            id = qId,
            categoryId = "java",
            title = "Test Question",
            prompt = "What is the expected behavior?",
            opt0 = opt0,
            opt1 = opt1,
            opt2 = opt2,
            opt3 = opt3,
            correctIndex = originalCorrectIndex,
            explanation = "Explanation",
            difficulty = "Intermediate",
            tags = "java,test"
        )
        assertEquals(prepared.optionsJson, preparedAgain.optionsJson)
        assertEquals(prepared.correctAnswerIndex, preparedAgain.correctAnswerIndex)
    }

    @Test
    fun testOptionDistributionIsBalancedAcrossLetters() {
        val counts = IntArray(4)
        val sampleSize = 400

        for (i in 0 until sampleSize) {
            val prepared = QuestionHelper.q(
                id = "sample_q_$i",
                categoryId = "test",
                title = "Question $i",
                prompt = "Prompt $i",
                opt0 = "Wrong A",
                opt1 = "Correct Answer",
                opt2 = "Wrong C",
                opt3 = "Wrong D",
                correctIndex = 1,
                explanation = "Exp",
                difficulty = "Beginner",
                tags = "test"
            )
            counts[prepared.correctAnswerIndex]++
        }

        // Each letter (A=0, B=1, C=2, D=3) should receive roughly 25% (at least 15% and at most 35%)
        for (i in 0..3) {
            val percentage = (counts[i].toDouble() / sampleSize) * 100
            assertTrue("Option index $i frequency was $percentage%, expected ~25%", percentage in 15.0..35.0)
        }
    }

    @Test
    fun testTechnicalConceptCatalogProvidesFlexibleDistributions() {
        val javaConcepts = TechnicalConceptCatalog.getConceptsForDomain("java")
        assertTrue("Java should have concepts defined", javaConcepts.isNotEmpty())

        val springConcepts = TechnicalConceptCatalog.getConceptsForDomain("spring_boot")
        assertTrue("Spring Boot should have concepts defined", springConcepts.isNotEmpty())

        // Ensure concepts have varying natural counts rather than rigid 100/100/100
        val javaCounts = javaConcepts.map { it.questionCount }
        assertFalse("Concept counts should vary naturally according to interview topics", javaCounts.all { it == 100 })

        // Check concept resolution
        val oop = TechnicalConceptCatalog.findConcept("java_oop")
        assertNotNull(oop)
        assertEquals("OOP Concepts & Fundamentals", oop?.name)
        assertEquals("java", TechnicalConceptCatalog.getDomainForConcept("java_oop"))

        // Check keyword matching
        assertTrue(
            TechnicalConceptCatalog.matchesConcept(
                oop!!,
                title = "Polymorphism and Dynamic Dispatch",
                prompt = "How does JVM handle virtual method invocation?",
                tags = listOf("java", "oop", "polymorphism")
            )
        )
    }

    @Test
    fun testConceptScoringAndHighlightProperties() {
        val oop = TechnicalConceptCatalog.findConcept("java_oop")
        assertNotNull(oop)

        // Initial unattempted state
        assertFalse(oop!!.hasScore)
        assertEquals("54", oop.scoreDisplay)
        assertFalse(oop.isLastAttempted)

        // Attempted concept with score e.g. 43/54 and highlighted
        val scoredConcept = oop.copy(
            userScore = 43,
            totalQuestionsAttempted = 54,
            isLastAttempted = true
        )

        assertTrue(scoredConcept.hasScore)
        assertEquals("43/54", scoredConcept.scoreDisplay)
        assertTrue(scoredConcept.isLastAttempted)
        assertEquals(43, scoredConcept.userScore)
        assertEquals(54, scoredConcept.totalQuestionsAttempted)
    }

    @Test
    fun testSystemDesignQuestionsAndConcepts() {
        val sysConcepts = TechnicalConceptCatalog.getConceptsForDomain("system_design")
        assertEquals(6, sysConcepts.size)

        val totalConceptQuestions = sysConcepts.sumOf { it.questionCount }
        assertEquals(80, totalConceptQuestions)

        val questions = com.example.data.local.questions.SystemDesignQuestions.getAll()
        assertEquals(80, questions.size)

        // Verify each question has 4 options, valid correctAnswerIndex, and non-blank content
        for (q in questions) {
            val options = JSONArray(q.optionsJson)
            assertEquals(4, options.length())
            assertTrue(q.correctAnswerIndex in 0..3)
            assertTrue(q.title.isNotBlank())
            assertTrue(q.prompt.isNotBlank())
            assertTrue(q.explanation.isNotBlank())
            assertEquals("system_design", q.categoryId)
        }

        // Verify concept resolution
        val blueprints = TechnicalConceptCatalog.findConcept("sys_blueprints")
        assertNotNull(blueprints)
        assertEquals("Real-World System Blueprints", blueprints?.name)
        assertEquals("system_design", TechnicalConceptCatalog.getDomainForConcept("sys_blueprints"))

        assertTrue(
            TechnicalConceptCatalog.matchesConcept(
                blueprints!!,
                title = "URL Shortener: Base62 Encoding",
                prompt = "Why is Base62 preferred for TinyURL?",
                tags = listOf("System Design", "TinyURL", "Base62")
            )
        )
    }

    @Test
    fun testDevopsQuestionsAndConcepts() {
        val devopsConcepts = TechnicalConceptCatalog.getConceptsForDomain("devops")
        assertEquals(6, devopsConcepts.size)

        val totalConceptQuestions = devopsConcepts.sumOf { it.questionCount }
        assertEquals(80, totalConceptQuestions)

        val questions = com.example.data.local.questions.DevopsQuestions.getAll()
        assertEquals(80, questions.size)

        // Verify each question has 4 options, valid correctAnswerIndex, and non-blank content
        for (q in questions) {
            val options = JSONArray(q.optionsJson)
            assertEquals(4, options.length())
            assertTrue(q.correctAnswerIndex in 0..3)
            assertTrue(q.title.isNotBlank())
            assertTrue(q.prompt.isNotBlank())
            assertTrue(q.explanation.isNotBlank())
            assertEquals("devops", q.categoryId)
        }

        // Verify concept resolution
        val k8s = TechnicalConceptCatalog.findConcept("devops_k8s")
        assertNotNull(k8s)
        assertEquals("Kubernetes Orchestration", k8s?.name)
        assertEquals("devops", TechnicalConceptCatalog.getDomainForConcept("devops_k8s"))

        assertTrue(
            TechnicalConceptCatalog.matchesConcept(
                k8s!!,
                title = "Kubernetes Pod Lifecycle: CrashLoopBackOff",
                prompt = "What indicates a CrashLoopBackOff state in a Kubernetes Pod?",
                tags = listOf("DevOps", "Kubernetes", "Pod", "Troubleshooting")
            )
        )
    }
}
