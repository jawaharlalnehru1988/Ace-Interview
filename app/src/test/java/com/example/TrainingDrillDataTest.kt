package com.example

import com.example.data.local.dsa.TrainingDrillData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TrainingDrillDataTest {

    @Test
    fun testTrainingTopicsCompleteness() {
        val topics = TrainingDrillData.getTopics()
        assertEquals("Must have exactly 5 core training tracks", 5, topics.size)

        val topicIds = topics.map { it.id }.toSet()
        assertTrue("Must include arrays track", topicIds.contains("arrays"))
        assertTrue("Must include strings track", topicIds.contains("strings"))
        assertTrue("Must include recursion track", topicIds.contains("recursion"))
        assertTrue("Must include linked_list track", topicIds.contains("linked_list"))
        assertTrue("Must include dp track", topicIds.contains("dp"))

        // Verify each topic has at least 30 drills
        topics.forEach { topic ->
            assertTrue(
                "Topic '${topic.id}' must have at least 30 drills (has ${topic.totalCount})",
                topic.totalCount >= 30
            )
            assertEquals("Drills list size must match totalCount", topic.totalCount, topic.drills.size)
        }
    }

    @Test
    fun testAllDrillsQualityAndStrictProgression() {
        val allDrills = TrainingDrillData.getAllDrills()
        assertTrue("Must have at least 150 progressive drills (minimum 30 per topic)", allDrills.size >= 150)

        val drillIds = mutableSetOf<String>()

        val drillsByTopic = allDrills.groupBy { it.topicId }
        assertEquals("Must have drills for all 5 topics", 5, drillsByTopic.size)

        drillsByTopic.forEach { (topicId, drills) ->
            assertTrue("Topic '$topicId' must have minimum 30 drills", drills.size >= 30)

            // Verify sequential lesson numbering 1 to N
            drills.forEachIndexed { index, drill ->
                assertEquals(
                    "Drills in topic '$topicId' must be sequentially numbered starting at 1",
                    index + 1,
                    drill.lessonNumber
                )
            }

            for (drill in drills) {
                assertTrue("Drill ID must be unique: ${drill.id}", drillIds.add(drill.id))
                assertEquals("Drill topicId must match group", topicId, drill.topicId)

                assertTrue("Title must not be blank for ${drill.id}", drill.title.isNotBlank())
                assertTrue("Subtitle must not be blank for ${drill.id}", drill.subtitle.isNotBlank())
                assertTrue("Concept delta must not be blank for ${drill.id}", drill.conceptDelta.isNotBlank())
                assertTrue("Intuition must not be blank for ${drill.id}", drill.intuition.isNotBlank())
                assertTrue("Code pattern must not be blank for ${drill.id}", drill.codePattern.isNotBlank())

                // Step trace checks
                assertTrue("Drill ${drill.id} must have step-by-step traces", drill.stepTrace.size >= 2)
                for (step in drill.stepTrace) {
                    assertTrue("Step number must be positive", step.stepNumber > 0)
                    assertTrue("Step state must not be blank", step.state.isNotBlank())
                    assertTrue("Step explanation must not be blank", step.explanation.isNotBlank())
                }

                // Quiz checks
                val quiz = drill.quizQuestion
                assertNotNull("Quiz question must exist for ${drill.id}", quiz)
                assertTrue("Quiz question prompt must not be blank", quiz.question.isNotBlank())
                assertTrue("Quiz must have at least 2 options", quiz.options.size >= 2)
                assertTrue(
                    "Correct option index must be within bounds: ${quiz.correctOptionIndex} for options of size ${quiz.options.size}",
                    quiz.correctOptionIndex in 0 until quiz.options.size
                )
                assertTrue("Quiz explanation must not be blank", quiz.explanation.isNotBlank())
            }
        }
    }

    @Test
    fun testArraysProgressionDetails() {
        val arrayDrills = TrainingDrillData.getDrillsByTopic("arrays")
        assertEquals(30, arrayDrills.size)

        // Lesson 1 is accumulator
        assertTrue(arrayDrills[0].title.contains("Accumulator", ignoreCase = true))
        // Lesson 2 is read-write pointer
        assertTrue(arrayDrills[1].title.contains("Write", ignoreCase = true) || arrayDrills[1].conceptDelta.contains("write", ignoreCase = true))
        // Lesson 3 is opposing pointers
        assertTrue(arrayDrills[2].title.contains("Opposing", ignoreCase = true) || arrayDrills[2].conceptDelta.contains("opposite", ignoreCase = true))
        // Lesson 4 is sliding window
        assertTrue(arrayDrills[3].title.contains("Window", ignoreCase = true))
        // Lesson 5 is prefix sum / hash map
        assertTrue(arrayDrills[4].title.contains("Prefix", ignoreCase = true) || arrayDrills[4].title.contains("Hash", ignoreCase = true))

        // Check milestones
        assertEquals("arr_drill_01", arrayDrills[0].id)
        assertEquals("arr_drill_30", arrayDrills[29].id)
        assertTrue(arrayDrills[29].title.contains("Boyer-Moore", ignoreCase = true))
    }

    @Test
    fun testStringsProgressionDetails() {
        val stringDrills = TrainingDrillData.getDrillsByTopic("strings")
        assertEquals(30, stringDrills.size)

        // Lesson 1: Direct frequency
        assertTrue(stringDrills[0].title.contains("Frequency", ignoreCase = true))
        // Lesson 2: Symmetrical matching
        assertTrue(stringDrills[1].title.contains("Matching", ignoreCase = true) || stringDrills[1].title.contains("Inward", ignoreCase = true))

        // Check milestones
        assertEquals("str_drill_01", stringDrills[0].id)
        assertEquals("str_drill_30", stringDrills[29].id)
    }

    @Test
    fun testRecursionProgressionDetails() {
        val recursionDrills = TrainingDrillData.getDrillsByTopic("recursion")
        assertEquals(30, recursionDrills.size)

        // Lesson 1: Base case
        assertTrue(recursionDrills[0].title.contains("Base Case", ignoreCase = true))
        // Lesson 2: Tree branching
        assertTrue(recursionDrills[1].title.contains("Branching", ignoreCase = true))
        // Lesson 3: Parameter accumulator
        assertTrue(recursionDrills[2].title.contains("Parameter", ignoreCase = true) || recursionDrills[2].title.contains("Accumulator", ignoreCase = true))
        // Lesson 4: Backtracking undo
        assertTrue(recursionDrills[3].title.contains("Backtrack", ignoreCase = true) || recursionDrills[3].title.contains("Undo", ignoreCase = true))

        // Check milestones
        assertEquals("rec_drill_01", recursionDrills[0].id)
        assertEquals("rec_drill_30", recursionDrills[29].id)
    }

    @Test
    fun testLinkedListProgressionDetails() {
        val linkedListDrills = TrainingDrillData.getDrillsByTopic("linked_list")
        assertEquals(30, linkedListDrills.size)

        // Lesson 1: Safe traversal
        assertTrue(linkedListDrills[0].title.contains("Safe Pointer", ignoreCase = true))
        // Lesson 2: 3-Pointer reversal
        assertTrue(linkedListDrills[1].title.contains("Reversal", ignoreCase = true))

        // Check milestones
        assertEquals("ll_drill_01", linkedListDrills[0].id)
        assertEquals("ll_drill_30", linkedListDrills[29].id)
    }

    @Test
    fun testDpProgressionDetails() {
        val dpDrills = TrainingDrillData.getDrillsByTopic("dp")
        assertEquals(30, dpDrills.size)

        // Lesson 1: Memoization
        assertTrue(dpDrills[0].title.contains("Memoization", ignoreCase = true))
        // Lesson 2: Tabulation
        assertTrue(dpDrills[1].title.contains("Tabulation", ignoreCase = true))
        // Lesson 3: Space optimization
        assertTrue(dpDrills[2].title.contains("Space", ignoreCase = true) || dpDrills[2].title.contains("Rolling", ignoreCase = true))

        // Check milestones
        assertEquals("dp_drill_01", dpDrills[0].id)
        assertEquals("dp_drill_30", dpDrills[29].id)
    }
}
