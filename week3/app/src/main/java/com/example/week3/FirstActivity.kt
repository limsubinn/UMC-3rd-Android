package com.example.week3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.week3.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFirstBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 콜백 선언
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val mString = result.data?.getStringExtra("mString")
                Toast.makeText(this, mString, Toast.LENGTH_SHORT).show()
            }
        }

        // 버튼 클릭 리스너
        viewBinding.button1.setOnClickListener {
            var text1 = viewBinding.text1.text.toString() // text1의 text값을 String으로 변환해서 저장

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("text1", text1) // intent에 text1 넣기
//            startActivity(intent)
            getResultText.launch(intent)
        }
    }
}