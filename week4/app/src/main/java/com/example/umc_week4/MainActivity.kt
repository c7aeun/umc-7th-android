package com.example.umc_week4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.umc_week4.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var total = 0
    var started = false
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonStart.setOnClickListener { start() }
        binding.buttonStop.setOnClickListener { stop() }
        binding.buttonEnd.setOnClickListener { end() }
    }

    val SETTIME = 51
    val RESET = 52

    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                SETTIME -> {
                    binding.textTimer.text = formatTime(msg.arg1)
                }

                RESET -> {
                    binding.textTimer.text = formatTime(0)
                }
            }
        }
    }

    fun start() {
        started = true
        thread(start = true) {
            while (true) {
                Thread.sleep(1000)
                if (!started) break
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
        total = 0
        binding.textTimer.text = formatTime(0)
    }

    fun formatTime(time: Int): String {
        val minute = String.format(Locale.KOREA, "%02d", time / 60)
        val second = String.format(Locale.KOREA, "%02d", time % 60)
        return "$minute : $second"
    }
}