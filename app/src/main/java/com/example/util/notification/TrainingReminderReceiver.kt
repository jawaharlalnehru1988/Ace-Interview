package com.example.util.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.MainActivity
import com.example.data.local.database.AceInterviewDatabase
import com.example.util.training.DailyTrainingScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class TrainingReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val requestCode = intent?.getIntExtra("request_code", ReminderScheduler.REQUEST_CODE_EVENING)
            ?: ReminderScheduler.REQUEST_CODE_EVENING
        val isMorning = (requestCode == ReminderScheduler.REQUEST_CODE_MORNING)
        val slotLabel = if (isMorning) "Morning Drill" else "Evening Drill"
        Log.d(TAG, "Received alarm broadcast for [$slotLabel]")

        // Reschedule next day's alarm for this specific slot
        if (isMorning) {
            ReminderScheduler.scheduleAlarm(
                context,
                ReminderScheduler.REQUEST_CODE_MORNING,
                ReminderScheduler.MORNING_HOUR,
                ReminderScheduler.MORNING_MINUTE,
                "Morning Drill"
            )
        } else {
            ReminderScheduler.scheduleAlarm(
                context,
                ReminderScheduler.REQUEST_CODE_EVENING,
                ReminderScheduler.EVENING_HOUR,
                ReminderScheduler.EVENING_MINUTE,
                "Evening Drill"
            )
        }

        val notificationId = if (isMorning) 9001 else 9002

        // Check training status and dispatch notification with deep link to practice section
        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val database = AceInterviewDatabase.getInstance(context)
                val sessions = database.quizDao().getAllSessions().firstOrNull() ?: emptyList()
                val attempts = database.quizDao().getAllAttempts().firstOrNull() ?: emptyList()

                val todayMcq = DailyTrainingScheduler.getTodayMcqTraining(
                    sessions = sessions,
                    attempts = attempts
                )

                showNotification(
                    context = context,
                    isMorning = isMorning,
                    categoryName = todayMcq.category,
                    title = todayMcq.title,
                    targetConceptId = todayMcq.targetConceptId ?: todayMcq.targetId,
                    notificationId = notificationId
                )
            } catch (e: Exception) {
                Log.e(TAG, "Error checking daily training status in reminder receiver", e)
            } finally {
                pendingResult.finish()
            }
        }
    }

    private fun showNotification(
        context: Context,
        isMorning: Boolean,
        categoryName: String,
        title: String,
        targetConceptId: String?,
        notificationId: Int
    ) {
        val channelId = "daily_training_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Daily Training Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Daily morning & evening reminders for interview practice drills"
            }
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }

        val openIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra("navigate_to", "practice_quiz")
            putExtra("category_id", targetConceptId ?: "all")
            putExtra("category_name", title)
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notifTitle = if (isMorning) "Morning Interview Drill ☀️" else "Evening Practice Drill 🎯"
        val notifSubtitle = if (isMorning) {
            "Kickstart your day with $title! Tap to practice now."
        } else {
            "Keep your streak alive! Today's $title is waiting for you."
        }
        val notifBigText = if (isMorning) {
            "Ready for a quick drill? Practice $categoryName ($title) to sharpen your problem-solving skills today."
        } else {
            "Don't miss your daily progress! Spend 5 minutes on $categoryName ($title) before the day ends."
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(notifTitle)
            .setContentText(notifSubtitle)
            .setStyle(NotificationCompat.BigTextStyle().bigText(notifBigText))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                NotificationManagerCompat.from(context).notify(notificationId, notification)
            } catch (e: SecurityException) {
                Log.e(TAG, "Missing POST_NOTIFICATIONS permission", e)
            }
        }
    }

    companion object {
        private const val TAG = "TrainingReminderReceiver"
    }
}
