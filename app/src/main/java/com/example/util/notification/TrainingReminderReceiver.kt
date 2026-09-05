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
        Log.d(TAG, "Received alarm broadcast for 8:00 PM reminder check")

        // Schedule next day's 8:00 PM reminder
        ReminderScheduler.scheduleDailyReminder(context, 20, 0)

        // Check if user has already completed today's MCQ practice
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

                if (!todayMcq.isCompleted) {
                    showNotification(context, todayMcq.category, todayMcq.title)
                } else {
                    Log.d(TAG, "Today's MCQ training already completed. Skipping reminder notification.")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error checking daily training status in reminder receiver", e)
            } finally {
                pendingResult.finish()
            }
        }
    }

    private fun showNotification(context: Context, categoryName: String, title: String) {
        val channelId = "daily_training_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Daily Training Reminder",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Daily 8:00 PM reminder for unattempted interview training drills"
            }
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }

        val openIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Daily MCQ Drill Reminder 🎯")
            .setContentText("Don't break your streak! Today's $title is waiting for you.")
            .setStyle(NotificationCompat.BigTextStyle().bigText(
                "You haven't attempted today's $categoryName training ($title). Take a few minutes to complete today's drills!"
            ))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
            } catch (e: SecurityException) {
                Log.e(TAG, "Missing POST_NOTIFICATIONS permission", e)
            }
        }
    }

    companion object {
        private const val TAG = "TrainingReminderReceiver"
        private const val NOTIFICATION_ID = 9001
    }
}
