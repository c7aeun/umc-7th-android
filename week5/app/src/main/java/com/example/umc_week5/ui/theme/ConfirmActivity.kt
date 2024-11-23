package com.example.umc_week5.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_week5.databinding.ActivityConfirmBinding // 바인딩 클래스 import

class ConfirmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmBinding // 바인딩 객체 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩 초기화
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트로부터 메모 내용을 받아 TextView에 설정
        val memoContent = intent.getStringExtra("MEMO_CONTENT")
        binding.textViewMemoContent.text = memoContent ?: "메모가 없습니다."
    }
}