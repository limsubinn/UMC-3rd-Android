package com.example.week8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week8.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 저장 버튼 -> EditText 값을 db에 insert 후 메인액티비티로 이동
        binding.btnSave.setOnClickListener {
            var data = binding.editText.text.toString()
            val roomDb = AppDatabase.getInstance(this)

            if (roomDb != null) {
                val memo = Memo(data, 0)
                roomDb.memoDao().insert(memo)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}