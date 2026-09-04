package com.example

import com.example.data.local.interview.InterviewQuestionCatalog
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class InterviewQuestionsCatalogTest {

    @Test
    fun testAllInterviewQuestionsAreValidAndRich() {
        val questions = InterviewQuestionCatalog.getAllQuestions()
        assertTrue("Should have a rich set of interview questions", questions.size >= 40)

        for (q in questions) {
            assertTrue("Question id must not be blank", q.id.isNotBlank())
            assertTrue("Title must not be blank for ${q.id}", q.title.isNotBlank())
            assertTrue("Question prompt must not be blank for ${q.id}", q.question.isNotBlank())
            assertTrue("Short reference answer must not be blank for ${q.id}", q.shortAnswer.isNotBlank())
            assertTrue("Short answer should be concise yet substantive for ${q.id}", q.shortAnswer.length >= 50)
            assertTrue("Concept ID must not be blank for ${q.id}", q.conceptId.isNotBlank())
            assertTrue("Concept Name must not be blank for ${q.id}", q.conceptName.isNotBlank())
            assertTrue("Key rubric points must not be empty for ${q.id}", q.keyPoints.isNotEmpty())
        }
    }

    @Test
    fun testTechnicalTracksHaveConceptGroupedQuestions() {
        val tracksToTest = listOf(
            "java_interview",
            "spring_boot_interview",
            "microservices_interview",
            "system_design_interview",
            "devops_interview",
            "lld_interview",
            "sql_interview",
            "security_interview",
            "full_stack_interview"
        )

        for (trackId in tracksToTest) {
            val trackQuestions = InterviewQuestionCatalog.getQuestionsForTrack(trackId)
            assertTrue("Track $trackId should have questions", trackQuestions.isNotEmpty())

            val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack(trackId)
            assertTrue("Track $trackId should have concept groups", conceptGroups.isNotEmpty())

            val totalQuestionsInGroups = conceptGroups.sumOf { it.questions.size }
            assertTrue("All questions should belong to concept groups for $trackId", totalQuestionsInGroups == trackQuestions.size)

            for (group in conceptGroups) {
                assertTrue("Group name must not be blank in $trackId", group.conceptName.isNotBlank())
                assertTrue("Group should contain questions in $trackId", group.questions.isNotEmpty())
            }
        }
    }

    @Test
    fun testQuestionLookupById() {
        val javaQ = InterviewQuestionCatalog.findQuestionById("iq_java_001")
        assertNotNull(javaQ)
        assertTrue(javaQ!!.title.contains("Stack vs Heap"))

        val k8sQ = InterviewQuestionCatalog.findQuestionById("iq_devops_004")
        assertNotNull(k8sQ)
        assertTrue(k8sQ!!.title.contains("CrashLoopBackOff"))
    }
}
