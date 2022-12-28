package com.example.week4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.week4.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // layout 설정
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 작성한 메모 보여주기
        var bundle = intent.extras
        if (bundle != null) {
            var text = bundle.getString("text", "")
            binding.memo.setText(text)
        }
    }
}