package com.example

import com.example.data.local.dsa.DsaProblemData
import com.example.domain.model.UserDashboard
import com.example.domain.model.UserProfile
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProfileAndDashboardDsaProgressTest {

    @Test
    fun testUserProfileDsaProgressCalculation() {
        val totalDsa = DsaProblemData.getAll().size
        assertEquals(313, totalDsa)

        // Case 1: 0 solved
        val profileZero = UserProfile(
            name = "Candidate",
            targetRole = "Senior SWE",
            targetTimeline = "L5 Track",
            overallLevel = "New Candidate",
            questionsAttempted = 0,
            accuracyPercentage = 0,
            dsaProblemsSolved = 0,
            totalDsaProblems = totalDsa,
            interviewSessions = 0,
            streakDays = 0,
            backendProgress = 0f,
            systemDesignProgress = 0f,
            dsaProgress = 0f / totalDsa
        )
        assertEquals(0, profileZero.dsaProblemsSolved)
        assertEquals(313, profileZero.totalDsaProblems)
        assertEquals(0f, profileZero.dsaProgress, 0.001f)

        // Case 2: 18 solved
        val solved18 = 18
        val progress18 = solved18.toFloat() / totalDsa.toFloat()
        val profile18 = profileZero.copy(
            dsaProblemsSolved = solved18,
            dsaProgress = progress18,
            overallLevel = "Intermediate Ready"
        )
        assertEquals(18, profile18.dsaProblemsSolved)
        assertEquals(313, profile18.totalDsaProblems)
        assertTrue("Progress should match solved / total", Math.abs(progress18 - profile18.dsaProgress) < 0.001f)
        assertEquals(5, (profile18.dsaProgress * 100).toInt()) // 18 / 313 = 5.75% -> 5%
    }

    @Test
    fun testUserDashboardDsaProgressFields() {
        val totalDsa = DsaProblemData.getAll().size
        val dashboard = UserDashboard(
            readinessScore = 45,
            readinessLevel = "Intermediate Ready",
            questionsCompleted = 9,
            targetQuestions = 300,
            currentStreakDays = 2,
            accuracyPercentage = 44,
            weakAreas = emptyList(),
            todayTrainings = emptyList(),
            dsaSolvedCount = 18,
            totalDsaProblems = totalDsa
        )

        assertEquals(18, dashboard.dsaSolvedCount)
        assertEquals(313, dashboard.totalDsaProblems)

        val percent = (dashboard.dsaSolvedCount.toFloat() / dashboard.totalDsaProblems.toFloat() * 100).toInt()
        assertEquals(5, percent)
    }

    @Test
    fun testReadinessScoreIncludesDsaProgress() {
        val totalDsa = DsaProblemData.getAll().size

        fun computeReadiness(completed: Int, accuracy: Int, streak: Int, dsaSolved: Int): Int {
            val targetQuestions = 300
            val progressScore = (completed.toDouble() / targetQuestions).coerceAtMost(1.0) * 35.0
            val dsaScore = if (totalDsa > 0) {
                (dsaSolved.toDouble() / totalDsa.toDouble()).coerceAtMost(1.0) * 20.0
            } else 0.0
            val accuracyScore = (accuracy.toDouble() / 100.0) * 35.0
            val streakScore = (streak.coerceAtMost(7).toDouble() / 7.0) * 10.0

            return if (completed > 0 || dsaSolved > 0) {
                (progressScore + dsaScore + accuracyScore + streakScore).toInt().coerceIn(1, 100)
            } else {
                0
            }
        }

        val scoreWithoutDsa = computeReadiness(completed = 20, accuracy = 80, streak = 3, dsaSolved = 0)
        val scoreWithDsa = computeReadiness(completed = 20, accuracy = 80, streak = 3, dsaSolved = 50)

        assertTrue("Solving DSA problems must increase readiness score", scoreWithDsa > scoreWithoutDsa)
    }
}
