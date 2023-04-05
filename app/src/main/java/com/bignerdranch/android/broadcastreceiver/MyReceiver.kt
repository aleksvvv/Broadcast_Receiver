package com.bignerdranch.android.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        when (intent?.action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val turnOn = intent.getBooleanExtra("state",false)
                Toast.makeText(context, "AIRPLANE_MODE_CHANGED. Turned - $turnOn",
                    Toast.LENGTH_LONG).show()
            }
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "BATTERY_LOW", Toast.LENGTH_LONG).show()
            }
        }
    }
}