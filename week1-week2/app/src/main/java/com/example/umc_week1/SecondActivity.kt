package com.example.umc_week1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_week1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val text = intent.getStringExtra("text")
        binding.textView.text = text
    }
}