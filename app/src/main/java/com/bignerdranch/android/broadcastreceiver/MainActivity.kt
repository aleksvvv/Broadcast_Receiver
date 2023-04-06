package com.bignerdranch.android.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private val localBroadcastReceiver by lazy {
        LocalBroadcastManager.getInstance(this)
    }

    private lateinit var progressBar: ProgressBar
    var page = 0
//    private val receiver = MyReceiver()

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                "loaded" -> {
                    val tt = intent.getIntExtra("percent", 0)
                    progressBar.progress = tt
                }
                "Clicked" -> {

                    var count = intent.getIntExtra("state", 0)
                    Toast.makeText(
                        context, "Clicked count - $count",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }


    private val service = MyService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        Intent(this, MyService::class.java).apply {
            startService(this)
        }

        val intentFilter = IntentFilter().apply {
            addAction("Clicked")
            addAction("loaded")
        }
        localBroadcastReceiver.registerReceiver(receiver, intentFilter)
        findViewById<Button>(R.id.button).setOnClickListener {
            Intent("Clicked").apply {
                putExtra("state", page++)
                localBroadcastReceiver.sendBroadcast(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastReceiver.unregisterReceiver(receiver)
    }
}