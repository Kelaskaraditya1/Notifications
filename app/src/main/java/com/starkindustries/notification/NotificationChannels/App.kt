package com.starkindustries.notification.NotificationChannels

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class App :Application()
{
    val CHANNER_ID_1:String="CHANNEL_ID_1"
    val CHANNEL_ID_2:String="CHANNEL_ID_2"
    override fun onCreate() {
        super.onCreate()
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O_MR1)
        {
            var channel1=NotificationChannel(CHANNER_ID_1,"CHANNEL1",NotificationManager.IMPORTANCE_HIGH)
                channel1.description="This is important Channel"
            var channel2= NotificationChannel(CHANNEL_ID_2,"CHANNEL_2",NotificationManager.IMPORTANCE_LOW)
            channel2.description="This is low importance Channel"
            var manager:NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel1)
            manager.createNotificationChannel(channel2)
        }
    }
}