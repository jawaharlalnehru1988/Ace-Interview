package com.example.util.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.util.Calendar

object ReminderScheduler {

    private const val TAG = "ReminderScheduler"

    const val REQUEST_CODE_MORNING = 8001
    const val REQUEST_CODE_EVENING = 8002

    const val MORNING_HOUR = 10
    const val MORNING_MINUTE = 0

    const val EVENING_HOUR = 20
    const val EVENING_MINUTE = 0

    /**
     * Schedules daily 2x practice reminders:
     * 1. Morning Practice Drill (10:00 AM)
     * 2. Evening Practice Drill (08:00 PM)
     */
    fun scheduleDailyReminders(context: Context) {
        scheduleAlarm(
            context = context,
            requestCode = REQUEST_CODE_MORNING,
            hourOfDay = MORNING_HOUR,
            minute = MORNING_MINUTE,
            slotLabel = "Morning Drill"
        )
        scheduleAlarm(
            context = context,
            requestCode = REQUEST_CODE_EVENING,
            hourOfDay = EVENING_HOUR,
            minute = EVENING_MINUTE,
            slotLabel = "Evening Drill"
        )
    }

    fun scheduleAlarm(
        context: Context,
        requestCode: Int,
        hourOfDay: Int,
        minute: Int,
        slotLabel: String
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return

        val intent = Intent(context, TrainingReminderReceiver::class.java).apply {
            putExtra("request_code", requestCode)
            putExtra("slot_label", slotLabel)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            } else {
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
            Log.d(TAG, "[$slotLabel] reminder scheduled for: ${calendar.time}")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to schedule alarm for $slotLabel", e)
        }
    }

    // Retained for backward compatibility
    fun scheduleDailyReminder(context: Context, hourOfDay: Int = 20, minute: Int = 0) {
        scheduleDailyReminders(context)
    }

    fun cancelAllReminders(context: Context) {
        cancelAlarm(context, REQUEST_CODE_MORNING)
        cancelAlarm(context, REQUEST_CODE_EVENING)
    }

    fun cancelReminder(context: Context) {
        cancelAllReminders(context)
    }

    private fun cancelAlarm(context: Context, requestCode: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, TrainingReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        )
        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent)
            pendingIntent.cancel()
        }
    }
}

