package com.bignerdranch.android.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        when (intent?.action) {
//            "loaded"->{
//                val tt = intent.getIntExtra("percent",0)
//                Toast.makeText(context, "percent $tt",
//                    Toast.LENGTH_SHORT).show()
//            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val turnOn = intent.getBooleanExtra("state",false)
                Toast.makeText(context, "AIRPLANE_MODE_CHANGED. Turned - $turnOn",
                    Toast.LENGTH_LONG).show()
            }
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "BATTERY_LOW", Toast.LENGTH_LONG).show()
            }
//            "Clicked" ->{
//                var count = intent.getIntExtra("state",0)
//                Toast.makeText(context, "Clicked count - $count", Toast.LENGTH_SHORT).show()
//            }
        }
    }
}