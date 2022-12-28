package com.example.week3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.week3.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        var bundle = intent.extras // FirstActivity에서 넘어온 intent에 담겨있는 객체

        if (bundle != null) {
            val value = bundle.getString("text1", "")
            viewBinding.text2.setText(value) // text2에 FirstActivity에서 보내준 텍스트 설정
        }

        // 버튼 클릭 리스너
        viewBinding.button2.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    // 뒤로 가기 버튼
    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("mString", "back")
        setResult(RESULT_OK, intent)

        super.onBackPressed()
    }
}