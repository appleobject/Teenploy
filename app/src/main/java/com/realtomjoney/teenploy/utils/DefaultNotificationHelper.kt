package com.realtomjoney.teenploy.utils

import android.app.Notification
import android.app.Notification.DEFAULT_VIBRATE
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.DEFAULT_SOUND
import com.realtomjoney.teenploy.R

private const val CHANNEL_ID =0
private const val REQUEST_CODE = 0
private const val FLAGS = 0


interface NotificationHelper{
    fun startNotification(title: String,text: String, smallIcon: Int)


}

class DefaultNotificationHelper(private val context: Context) : NotificationHelper {


    // Register the notification manager with the system
    private fun registerNotificationManagerWithSystem(channel: NotificationChannel){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // create a notification channel and register it with the system
    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_LOW
            val notificationChannel = NotificationChannel(
                context.getString(R.string.notification_channel_id),
                name,
                importance
            ).apply {
                description = descriptionText
                setShowBadge(true)
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            }
            registerNotificationManagerWithSystem(notificationChannel)
        }
    }

    // create a basic notification
    private fun createNotificationInstance(title: String, text: String, smallIcon: Int) : NotificationCompat.Builder{

        return NotificationCompat.Builder(context, context.getString(R.string.notification_channel_id))
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(smallIcon)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setDefaults(DEFAULT_VIBRATE)
            .setDefaults(DEFAULT_SOUND)

    }

    // display the notification
    private fun notify(notificationCompat: NotificationCompat.Builder){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(CHANNEL_ID, notificationCompat.build())
    }

    override fun startNotification(title: String, text: String, smallIcon: Int) {
        createNotificationChannel()
        notify(createNotificationInstance(title,text,smallIcon))
    }

}



