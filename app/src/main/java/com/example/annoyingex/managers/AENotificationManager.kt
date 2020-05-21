package com.example.annoyingex.managers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.annoyingex.MainActivity
import com.example.annoyingex.R

class AENotificationManager(private val context: Context) {

    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    init {
        createChannel()
    }

    fun postNotif(message: String, index: Int) {
        val ughIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(NOTIFICATION_DATA, message)
        }

        val pendingUghIntent = PendingIntent.getActivity(context, 0, ughIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context,ANNOYING_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_person_black_24dp)
            .setContentTitle("Shravan Seshadri")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingUghIntent)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(index, notification)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "message category"
            val descriptionText = "Annoying messages come from here"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(ANNOYING_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            notificationManagerCompat.createNotificationChannel(channel)
        }
    }

    companion object {
        const val ANNOYING_CHANNEL_ID = "ANNOYING_CHANNEL_ID"
        const val NOTIFICATION_DATA = "NOTIFICATION_DATA"
    }
}