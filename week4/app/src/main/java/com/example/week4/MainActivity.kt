package com.example.week4

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.week4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var temp: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // layout 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 화면 전환
        binding.okButton.setOnClickListener {
            var text = binding.editText.text.toString()

            val intent = Intent(this, ShowActivity::class.java)
            intent.putExtra("text", text)
            startActivity(intent)
        }
    }

    override fun onStop() {
        // 메모를 입력하는 EditText의 현재 값을 전역변수에 담기
        temp = binding.editText.text.toString()
        // 메모를 입력하는 EditText 비우기
        binding.editText.setText("")

        super.onStop()
    }

    override fun onRestart() {
        // Dialog 설정
        val builder = AlertDialog.Builder(this)

        // 이어서 작성할 거냐고 묻는 창 띄우기
        builder.setMessage("메모를 이어서 작성하시겠습니까?")
            .setPositiveButton("예",
                DialogInterface.OnClickListener { dialog, id ->
                    // EditText를 temp 값으로 설정
                    binding.editText.setText(temp)
                    // 커서를 EditText의 맨 끝에 위치시키기
                    binding.editText.setSelection(binding.editText.length())
                })
            .setNegativeButton("아니요",
                DialogInterface.OnClickListener { dialog, id ->
                    // temp 값 비우기
                    temp = ""
                    // EditText 값 비우기
                    binding.editText.setText("")
                })
            .show()

        super.onRestart()
    }
}