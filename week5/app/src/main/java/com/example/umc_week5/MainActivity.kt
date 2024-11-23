package com.example.umc_week5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // 전역 변수로 메모 내용을 저장하는 변수 선언
    private var memoContent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // EditText와 Button 연결
        val editTextMemo = findViewById<EditText>(R.id.editTextMemo)
        val buttonConfirm = findViewById<Button>(R.id.buttonConfirm)

        // 버튼을 클릭하면 ConfirmActivity로 이동
        buttonConfirm.setOnClickListener {
            val intent = Intent(this, ConfirmActivity::class.java)
            intent.putExtra("MEMO_CONTENT", editTextMemo.text.toString())
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        // memoContent가 null이 아닌 경우, EditText에 설정
        val editTextMemo = findViewById<EditText>(R.id.editTextMemo)
        if (!memoContent.isNullOrEmpty()) {
            editTextMemo.setText(memoContent)
        }
    }

    override fun onPause() {
        super.onPause()

        // 현재 EditText에 작성된 내용을 memoContent에 저장
        val editTextMemo = findViewById<EditText>(R.id.editTextMemo)
        memoContent = editTextMemo.text.toString()
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
            }
            show()
        }
    }
}