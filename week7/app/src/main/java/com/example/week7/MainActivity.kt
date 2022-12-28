package com.example.week7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.week7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 범위 설정
        binding.npHour.maxValue = 23
        binding.npHour.minValue = 0
        binding.npMin.maxValue = 59
        binding.npMin.minValue = 0
        binding.npSec.maxValue = 59
        binding.npSec.minValue = 0

        // 실행 버튼
        binding.btnRun.setOnClickListener {
            // 시간, 분, 초의 값 저장
            var hour = Integer.parseInt(binding.npHour.value.toString())
            var min = Integer.parseInt(binding.npMin.value.toString())
            var sec = Integer.parseInt(binding.npSec.value.toString())

            if ((hour == 0) && (min == 0) && (sec == 0)) {
                // 설정한 시간이 없을 때 타이머를 실행하지 않는다.
                Toast.makeText(this, "시간을 설정해주세요.", LENGTH_SHORT).show()
            } else {
                // 액티비티 이동 및 값 전달
                val intent = Intent(this, RunActivity::class.java)
                intent.putExtra("hour", hour)
                intent.putExtra("min", min)
                intent.putExtra("sec", sec)
                startActivity(intent)
                finish()
            }
        }

    }
}