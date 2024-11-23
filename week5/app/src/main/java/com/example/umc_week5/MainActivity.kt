package com.example.umc_week5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_week5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 전역 변수로 메모 내용을 저장하는 변수 선언
    private var memoContent: String? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼을 클릭하면 ConfirmActivity로 이동
        binding.buttonConfirm.setOnClickListener {
            val intent = Intent(this, ConfirmActivity::class.java)
            val text = binding.editTextMemo.text.toString()
            intent.putExtra("MEMO_CONTENT", text)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        // memoContent가 null이 아닌 경우, EditText에 설정
        if (!memoContent.isNullOrEmpty()) {
            binding.editTextMemo.setText(memoContent)
        }
    }

    override fun onPause() {
        super.onPause()

        // 현재 EditText에 작성된 내용을 memoContent에 저장
        memoContent = binding.editTextMemo.text.toString()
    }

    override fun onRestart() {
        super.onRestart()

        // 다시 작성할 것인지 묻는 AlertDialog 표시
        AlertDialog.Builder(this).apply {
            setTitle("다시 작성하시겠습니까?")
            setMessage("작성 중이던 메모를 다시 작성하시겠습니까?")
            setPositiveButton("예") { _, _ ->
                // 아무 작업도 하지 않고 메모 유지
            }
            setNegativeButton("아니오") { _, _ ->
                // memoContent 내용을 비워 초기화
                memoContent = ""
                binding.editTextMemo.text = null
            }
            show()
        }
    }
}