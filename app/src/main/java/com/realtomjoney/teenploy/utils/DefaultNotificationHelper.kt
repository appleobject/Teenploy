package com.realtomjoney.teenploy.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.realtomjoney.teenploy.MainActivity
import com.realtomjoney.teenploy.R

private const val CHANNEL_ID =0
private const val REQUEST_CODE = 0
private const val FLAGS = 0


class DefaultNotificationHelper (private val messageBody: String, private val applicationContext: Context){

//    @RequiresApi(Build.VERSION_CODES.O)

    fun createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = applicationContext.getString(R.string.channel_name)
            val descriptionText = applicationContext.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(
                applicationContext.getString(R.string.notification_channel_id),
                name,
                importance
            ).apply {
                description = descriptionText
            }

            // Register the channel with the system
            val notificationManagerCompat =
                applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManagerCompat.createNotificationChannel(channel)

            // create an explicit intent for the tap action
            val intent = Intent(applicationContext, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)


            // create a basic notification
            val builder = NotificationCompat.Builder(
                applicationContext,
                applicationContext.getString(R.string.notification_channel_id)
            )
                .setContentTitle(applicationContext.getString(R.string.notification_title))
                .setContentText(messageBody)
                .setSmallIcon(R.drawable.ic_mail)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            // Show the notification
            notificationManagerCompat.notify(CHANNEL_ID, builder.build())
        }



    }

}


