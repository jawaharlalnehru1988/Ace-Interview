package com.example

import com.example.util.notification.ReminderScheduler
import com.example.util.training.DailyTrainingScheduler
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class DailyReminderNotificationTest {

    @Test
    fun testTwiceDailyReminderConstants() {
        assertEquals(8001, ReminderScheduler.REQUEST_CODE_MORNING)
        assertEquals(8002, ReminderScheduler.REQUEST_CODE_EVENING)
        assertEquals(10, ReminderScheduler.MORNING_HOUR)
        assertEquals(0, ReminderScheduler.MORNING_MINUTE)
        assertEquals(20, ReminderScheduler.EVENING_HOUR)
        assertEquals(0, ReminderScheduler.EVENING_MINUTE)
    }

    @Test
    fun testDailyMcqTargetConceptForDeepLinking() {
        val training = DailyTrainingScheduler.getTodayMcqTraining(epochDay = 100L)
        assertNotNull(training.targetConceptId)
        assertTrue(training.targetConceptId.isNotEmpty())
        assertNotNull(training.title)
        assertTrue(training.title.isNotEmpty())
        assertNotNull(training.category)
    }
}
