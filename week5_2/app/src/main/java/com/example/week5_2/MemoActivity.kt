package com.example.week5_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week5_2.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 저장 버튼 -> 데이터 보내기
        binding.btnSave.setOnClickListener {
            var data = binding.editText.text.toString()
            val intent = Intent()

            intent.putExtra("data", data)
            setResult(RESULT_OK, intent)

            finish()
        }
    }
}