package com.realtomjoney.teenploy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realtomjoney.teenploy.utils.DefaultNotificationHelper

class MainActivity : AppCompatActivity() {

    private lateinit var defaultNotificationHelper: DefaultNotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        defaultNotificationHelper = DefaultNotificationHelper(this)
        defaultNotificationHelper.startNotification(
           "Employee response",
            "You've got mail",
            R.drawable.ic_mail
        )


    }
}