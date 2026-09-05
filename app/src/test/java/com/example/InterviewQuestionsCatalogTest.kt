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
        assertTrue(javaQ!!.title.contains("Abstraction vs Encapsulation"))

        val k8sQ = InterviewQuestionCatalog.findQuestionById("iq_devops_043")
        assertNotNull(k8sQ)
        assertTrue(k8sQ!!.title.contains("CrashLoopBackOff"))
    }

    @Test
    fun testJavaTrackHasOver150Questions() {
        val javaQuestions = InterviewQuestionCatalog.getQuestionsForTrack("java_interview")
        assertTrue("Java track must have 150+ questions, found: ${javaQuestions.size}", javaQuestions.size >= 150)

        val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack("java_interview")
        assertTrue("Java track should have multiple distinct concept groups", conceptGroups.size >= 5)

        // Verify IDs are all unique
        val uniqueIds = javaQuestions.map { it.id }.toSet()
        assertTrue("All question IDs must be unique", uniqueIds.size == javaQuestions.size)
    }

    @Test
    fun testSpringBootTrackHasOver150Questions() {
        val springQuestions = InterviewQuestionCatalog.getQuestionsForTrack("spring_boot_interview")
        assertTrue("Spring Boot track must have 150+ questions, found: ${springQuestions.size}", springQuestions.size >= 150)

        val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack("spring_boot_interview")
        assertTrue("Spring Boot track should have multiple distinct concept groups", conceptGroups.size >= 5)

        // Verify IDs are all unique
        val uniqueIds = springQuestions.map { it.id }.toSet()
        assertTrue("All question IDs must be unique", uniqueIds.size == springQuestions.size)
    }

    @Test
    fun testMicroservicesTrackHasOver150Questions() {
        val msQuestions = InterviewQuestionCatalog.getQuestionsForTrack("microservices_interview")
        assertTrue("Microservices track must have 150+ questions, found: ${msQuestions.size}", msQuestions.size >= 150)

        val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack("microservices_interview")
        assertTrue("Microservices track should have multiple distinct concept groups", conceptGroups.size >= 5)

        // Verify IDs are all unique
        val uniqueIds = msQuestions.map { it.id }.toSet()
        assertTrue("All question IDs must be unique", uniqueIds.size == msQuestions.size)
    }

    @Test
    fun testFullStackTrackHasOver150Questions() {
        val fsQuestions = InterviewQuestionCatalog.getQuestionsForTrack("full_stack_interview")
        assertTrue("Full Stack track must have 150+ questions, found: ${fsQuestions.size}", fsQuestions.size >= 150)

        val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack("full_stack_interview")
        assertTrue("Full Stack track should have multiple distinct concept groups", conceptGroups.size >= 5)

        // Verify IDs are all unique
        val uniqueIds = fsQuestions.map { it.id }.toSet()
        assertTrue("All question IDs must be unique", uniqueIds.size == fsQuestions.size)
    }

    @Test
    fun testSystemDesignTrackHasOver150Questions() {
        val sysQuestions = InterviewQuestionCatalog.getQuestionsForTrack("system_design_interview")
        assertTrue("System Design track must have 150+ questions, found: ${sysQuestions.size}", sysQuestions.size >= 150)

        val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack("system_design_interview")
        assertTrue("System Design track should have multiple distinct concept groups", conceptGroups.size >= 5)

        // Verify IDs are all unique
        val uniqueIds = sysQuestions.map { it.id }.toSet()
        assertTrue("All question IDs must be unique", uniqueIds.size == sysQuestions.size)
    }

    @Test
    fun testHldTrackHasOver150Questions() {
        val hldQuestions = InterviewQuestionCatalog.getQuestionsForTrack("hld_interview")
        assertTrue("HLD track must have 150+ questions, found: ${hldQuestions.size}", hldQuestions.size >= 150)

        val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack("hld_interview")
        assertTrue("HLD track should have multiple distinct concept groups, found: ${conceptGroups.size}", conceptGroups.size >= 8)

        // Verify IDs are all unique
        val uniqueIds = hldQuestions.map { it.id }.toSet()
        assertTrue("All question IDs must be unique", uniqueIds.size == hldQuestions.size)

        // Verify alias tracks
        val aliasQuestions = InterviewQuestionCatalog.getQuestionsForTrack("hld")
        assertTrue("HLD alias track should return same rich questions", aliasQuestions.size == hldQuestions.size)

        // Verify every question has 5 rubric key points and valid data
        for (q in hldQuestions) {
            assertTrue("Question ${q.id} title must not be blank", q.title.isNotBlank())
            assertTrue("Question ${q.id} question text must not be blank", q.question.isNotBlank())
            assertTrue("Question ${q.id} shortAnswer must not be blank", q.shortAnswer.isNotBlank())
            assertTrue("Question ${q.id} must have exactly 5 rubric key points", q.keyPoints.size == 5)
        }
    }

    @Test
    fun testDevopsTrackHasOver150Questions() {
        val devopsQuestions = InterviewQuestionCatalog.getQuestionsForTrack("devops_interview")
        assertTrue("DevOps track must have 150+ questions, found: ${devopsQuestions.size}", devopsQuestions.size >= 150)

        val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack("devops_interview")
        assertTrue("DevOps track should have multiple distinct concept groups", conceptGroups.size >= 5)

        // Verify IDs are all unique
        val uniqueIds = devopsQuestions.map { it.id }.toSet()
        assertTrue("All question IDs must be unique", uniqueIds.size == devopsQuestions.size)
    }

    @Test
    fun testSqlTrackHasOver150Questions() {
        val sqlQuestions = InterviewQuestionCatalog.getQuestionsForTrack("sql_interview")
        assertTrue("SQL track must have 150+ questions, found: ${sqlQuestions.size}", sqlQuestions.size >= 150)

        val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack("sql_interview")
        assertTrue("SQL track should have multiple distinct concept groups", conceptGroups.size >= 5)

        // Verify IDs are all unique
        val uniqueIds = sqlQuestions.map { it.id }.toSet()
        assertTrue("All question IDs must be unique", uniqueIds.size == sqlQuestions.size)
    }

    @Test
    fun testAngularTrackHasOver150Questions() {
        val ngQuestions = InterviewQuestionCatalog.getQuestionsForTrack("angular_interview")
        assertTrue("Angular track must have 150+ questions, found: ${ngQuestions.size}", ngQuestions.size >= 150)

        val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack("angular_interview")
        assertTrue("Angular track should have multiple distinct concept groups, found: ${conceptGroups.size}", conceptGroups.size >= 8)

        // Verify IDs are all unique
        val uniqueIds = ngQuestions.map { it.id }.toSet()
        assertTrue("All question IDs must be unique", uniqueIds.size == ngQuestions.size)

        // Verify alias tracks
        val aliasQuestions = InterviewQuestionCatalog.getQuestionsForTrack("angular")
        assertTrue("Angular alias track should return same rich questions", aliasQuestions.size == ngQuestions.size)
        val frontendQuestions = InterviewQuestionCatalog.getQuestionsForTrack("frontend_interview")
        assertTrue("Frontend alias track should return same rich questions", frontendQuestions.size == ngQuestions.size)

        // Verify every question has 5 rubric key points and valid data
        for (q in ngQuestions) {
            assertTrue("Question ${q.id} title must not be blank", q.title.isNotBlank())
            assertTrue("Question ${q.id} question text must not be blank", q.question.isNotBlank())
            assertTrue("Question ${q.id} shortAnswer must not be blank", q.shortAnswer.isNotBlank())
            assertTrue("Question ${q.id} must have exactly 5 rubric key points", q.keyPoints.size == 5)
        }
    }

    @Test
    fun testLldTrackHasOver150Questions() {
        val lldQuestions = InterviewQuestionCatalog.getQuestionsForTrack("lld_interview")
        assertTrue("LLD track must have 150+ questions, found: ${lldQuestions.size}", lldQuestions.size >= 150)

        val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack("lld_interview")
        assertTrue("LLD track should have multiple distinct concept groups, found: ${conceptGroups.size}", conceptGroups.size >= 8)

        // Verify IDs are all unique
        val uniqueIds = lldQuestions.map { it.id }.toSet()
        assertTrue("All question IDs must be unique", uniqueIds.size == lldQuestions.size)

        // Verify alias tracks
        val aliasQuestions = InterviewQuestionCatalog.getQuestionsForTrack("lld")
        assertTrue("LLD alias track should return same rich questions", aliasQuestions.size == lldQuestions.size)

        // Verify every question has 5 rubric key points and valid data
        for (q in lldQuestions) {
            assertTrue("Question ${q.id} title must not be blank", q.title.isNotBlank())
            assertTrue("Question ${q.id} question text must not be blank", q.question.isNotBlank())
            assertTrue("Question ${q.id} shortAnswer must not be blank", q.shortAnswer.isNotBlank())
            assertTrue("Question ${q.id} difficulty must not be blank", q.difficulty.isNotBlank())
            assertTrue("Question ${q.id} must have exactly 5 rubric key points", q.keyPoints.size == 5)
        }
    }

    @Test
    fun testSecurityTrackHasOver150Questions() {
        val secQuestions = InterviewQuestionCatalog.getQuestionsForTrack("security_interview")
        assertTrue("Security track must have 150+ questions, found: ${secQuestions.size}", secQuestions.size >= 150)

        val conceptGroups = InterviewQuestionCatalog.getConceptGroupsForTrack("security_interview")
        assertTrue("Security track should have multiple distinct concept groups, found: ${conceptGroups.size}", conceptGroups.size >= 8)

        // Verify IDs are all unique
        val uniqueIds = secQuestions.map { it.id }.toSet()
        assertTrue("All question IDs must be unique", uniqueIds.size == secQuestions.size)

        // Verify alias tracks
        val aliasQuestions = InterviewQuestionCatalog.getQuestionsForTrack("security")
        assertTrue("Security alias track should return same rich questions", aliasQuestions.size == secQuestions.size)

        // Verify every question has 5 rubric key points and valid data
        for (q in secQuestions) {
            assertTrue("Question ${q.id} title must not be blank", q.title.isNotBlank())
            assertTrue("Question ${q.id} question text must not be blank", q.question.isNotBlank())
            assertTrue("Question ${q.id} shortAnswer must not be blank", q.shortAnswer.isNotBlank())
            assertTrue("Question ${q.id} difficulty must not be blank", q.difficulty.isNotBlank())
            assertTrue("Question ${q.id} must have exactly 5 rubric key points", q.keyPoints.size == 5)
        }
    }
}
