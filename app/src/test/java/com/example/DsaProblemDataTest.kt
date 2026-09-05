package com.example

import com.example.data.local.dsa.ArrayDsaProblems
import com.example.data.local.dsa.DsaProblemData
import com.example.data.local.dsa.RecursionDsaProblems
import com.example.data.local.dsa.StringDsaProblems
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DsaProblemDataTest {

    @Test
    fun testArrayProblemsCountAndQuality() {
        val problems = ArrayDsaProblems.getProblems()
        assertTrue("Array problems must have at least 100 questions", problems.size >= 100)
        assertEquals(100, problems.size)

        val ids = mutableSetOf<String>()
        var easyCount = 0
        var mediumCount = 0
        var hardCount = 0

        for (p in problems) {
            assertTrue("ID must be unique: ${p.id}", ids.add(p.id))
            assertEquals("Topic must be arrays", "arrays", p.topic)
            assertTrue("Title must not be blank: ${p.id}", p.title.isNotBlank())
            assertTrue("Pattern must not be blank: ${p.id}", p.pattern.isNotBlank())
            assertTrue("Description must not be blank: ${p.id}", p.description.isNotBlank())
            assertTrue("Key insight must not be blank: ${p.id}", p.keyInsight.isNotBlank())
            assertTrue("Solution code must not be blank: ${p.id}", p.solutionCode.isNotBlank())
            assertTrue("Time complexity must not be blank: ${p.id}", p.timeComplexity.isNotBlank())
            assertTrue("Space complexity must not be blank: ${p.id}", p.spaceComplexity.isNotBlank())

            when (p.difficulty.lowercase()) {
                "easy" -> easyCount++
                "medium" -> mediumCount++
                "hard" -> hardCount++
            }
        }

        assertTrue("Should have healthy portion of Easy problems", easyCount >= 30)
        assertTrue("Should have healthy portion of Medium problems", mediumCount >= 25)
        assertTrue("Hard problems should be controlled (< 25%)", hardCount <= 25)
    }

    @Test
    fun testStringProblemsCountAndQuality() {
        val problems = StringDsaProblems.getProblems()
        assertTrue("String problems must have at least 100 questions", problems.size >= 100)
        assertEquals(100, problems.size)

        val ids = mutableSetOf<String>()
        var easyCount = 0
        var mediumCount = 0
        var hardCount = 0

        for (p in problems) {
            assertTrue("ID must be unique: ${p.id}", ids.add(p.id))
            assertEquals("Topic must be strings", "strings", p.topic)
            assertTrue("Title must not be blank: ${p.id}", p.title.isNotBlank())
            assertTrue("Pattern must not be blank: ${p.id}", p.pattern.isNotBlank())
            assertTrue("Description must not be blank: ${p.id}", p.description.isNotBlank())
            assertTrue("Key insight must not be blank: ${p.id}", p.keyInsight.isNotBlank())
            assertTrue("Solution code must not be blank: ${p.id}", p.solutionCode.isNotBlank())

            when (p.difficulty.lowercase()) {
                "easy" -> easyCount++
                "medium" -> mediumCount++
                "hard" -> hardCount++
            }
        }

        assertTrue("Should have healthy portion of Easy problems", easyCount >= 30)
        assertTrue("Should have healthy portion of Medium problems", mediumCount >= 25)
        assertTrue("Hard problems should be controlled (< 25%)", hardCount <= 25)
    }

    @Test
    fun testRecursionProblemsCountAndQuality() {
        val problems = RecursionDsaProblems.getProblems()
        assertTrue("Recursion problems must have at least 100 questions", problems.size >= 100)
        assertEquals(100, problems.size)

        val ids = mutableSetOf<String>()
        var easyCount = 0
        var mediumCount = 0
        var hardCount = 0

        for (p in problems) {
            assertTrue("ID must be unique: ${p.id}", ids.add(p.id))
            assertEquals("Topic must be recursion", "recursion", p.topic)
            assertTrue("Title must not be blank: ${p.id}", p.title.isNotBlank())
            assertTrue("Pattern must not be blank: ${p.id}", p.pattern.isNotBlank())
            assertTrue("Description must not be blank: ${p.id}", p.description.isNotBlank())
            assertTrue("Key insight must not be blank: ${p.id}", p.keyInsight.isNotBlank())
            assertTrue("Solution code must not be blank: ${p.id}", p.solutionCode.isNotBlank())

            when (p.difficulty.lowercase()) {
                "easy" -> easyCount++
                "medium" -> mediumCount++
                "hard" -> hardCount++
            }
        }

        assertTrue("Should have healthy portion of Easy problems", easyCount >= 25)
        assertTrue("Should have healthy portion of Medium problems", mediumCount >= 40)
        assertTrue("Hard problems should be controlled (< 25%)", hardCount <= 25)
    }

    @Test
    fun testDsaProblemDataCatalogWiring() {
        val allProblems = DsaProblemData.getAll()
        val allIds = allProblems.map { it.id }.toSet()
        assertEquals("All IDs across entire catalog must be unique", allProblems.size, allIds.size)

        val arrayProblems = DsaProblemData.getByTopic("arrays")
        assertEquals(100, arrayProblems.size)

        val stringProblems = DsaProblemData.getByTopic("strings")
        assertEquals(100, stringProblems.size)

        val recursionProblems = DsaProblemData.getByTopic("recursion")
        assertEquals(100, recursionProblems.size)

        val linkedListProblems = DsaProblemData.getByTopic("linked_list")
        assertTrue("Linked list problems should be present", linkedListProblems.isNotEmpty())

        val treesProblems = DsaProblemData.getByTopic("trees")
        assertTrue("Trees problems should be present", treesProblems.isNotEmpty())

        val dpProblems = DsaProblemData.getByTopic("dp")
        assertTrue("DP problems should be present", dpProblems.isNotEmpty())

        assertTrue("Total catalog problems should exceed 300", allProblems.size >= 300)
        assertEquals(313, allProblems.size)
    }

    @Test
    fun testJavaCodeHighlighterIntegrity() {
        val sampleCode = """
            class Solution {
                // Return two indices
                public int[] twoSum(int[] nums, int target) {
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int i = 0; i < nums.length; i++) {
                        int comp = target - nums[i];
                        if (map.containsKey(comp)) return new int[] { map.get(comp), i };
                        map.put(nums[i], i);
                    }
                    return new int[0];
                }
            }
        """.trimIndent()

        val highlighted = com.example.presentation.common.JavaCodeHighlighter.highlight(sampleCode)
        assertEquals("Annotated text content must exactly equal original input", sampleCode, highlighted.text)
        assertTrue("Highlighted code must contain style spans", highlighted.spanStyles.isNotEmpty())

        // Verify across all problems in the catalog
        val allProblems = DsaProblemData.getAll()
        for (problem in allProblems) {
            val result = com.example.presentation.common.JavaCodeHighlighter.highlight(problem.solutionCode)
            assertEquals("Code integrity preserved for ${problem.id}", problem.solutionCode, result.text)
            assertTrue("Should have style spans for ${problem.id}", result.spanStyles.isNotEmpty())
        }
    }
}
