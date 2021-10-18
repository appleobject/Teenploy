package com.realtomjoney.teenploy

import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realtomjoney.teenploy.utils.DefaultNotificationHelper
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var defaultNotificationHelper: DefaultNotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        defaultNotificationHelper = DefaultNotificationHelper("Welcome",this)
        defaultNotificationHelper.createChannel()

    }
}