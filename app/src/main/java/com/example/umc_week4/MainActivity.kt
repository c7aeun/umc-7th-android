package com.example.umc_week4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread
import android.widget.TextView
import android.widget.Button
import android.os.Handler
import android.os.Looper
import android.os.Message

class MainActivity : AppCompatActivity() {


    var started = false
    private lateinit var textTimer: TextView
    private lateinit var buttonStart: Button
    private lateinit var buttonStop: Button
    private lateinit var buttonEnd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStart.setOnClickListener { start() }
        buttonStop.setOnClickListener { stop() }
        buttonEnd.setOnClickListener { end() }
    }

    val SETTIME = 51
    val RESET = 52

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                SETTIME -> {
                    textTimer.text = formatTime(msg.arg1)
                }
                RESET -> {
                    textTimer.text = "00 : 00"
                }
            }
        }
    }

    fun start() {
        started = true
        thread(start=true) {
            var total = 0
            while (true) {
                Thread.sleep(1000)
                if(!started) break
                total += 1
                val msg = Message()
                msg.what = SETTIME
                msg.arg1 = total
                handler.sendMessage(msg)
            }
        }
    }

    fun stop() {
        started = false
    }

    fun end() {
        started = false
        textTimer.text = "00 : 00"
    }

    fun formatTime(time:Int) : String {
        val minute = String.format("%02d", time/60)
        val second = String.format("%02d", time%60)
        return "$minute : $second"
    }
}