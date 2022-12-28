package com.example.week7

import android.content.Intent
import android.media.SoundPool
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.week7.databinding.ActivityRunBinding
import java.lang.Thread.sleep

class RunActivity : AppCompatActivity() {
    private val binding: ActivityRunBinding by lazy {
        ActivityRunBinding.inflate(layoutInflater)
    }

    private var hour: Int = 0
    private var min: Int = 0
    private var sec: Int = 0

    private var str_hour: String = "00"
    private var str_min: String = "00"
    private var str_sec: String = "00"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // soundpool build
        var sp = SoundPool.Builder().build()
        var sound = sp.load(this, R.raw.sound, 1)


        // 데이터 받기
        val bundle = intent.extras
        if (bundle != null) {
            hour = bundle.getInt("hour", 0)
            min = bundle.getInt("min", 0)
            sec = bundle.getInt("sec", 0)
        }

        // 텍스트 띄우기
        str_hour = String.format("%02d", hour)
        str_min = String.format("%02d", min)
        str_sec = String.format("%02d", sec)
        binding.tvTime.text = str_hour + ":" + str_min + ":" + str_sec

        // 시간, 분, 초 -> milliseconds로 저장
        var leftTime = Integer.toUnsignedLong((sec + min * 60 + hour * 60 * 60) * 1000)

        val handler = Handler(mainLooper)

        Thread() {

            while (leftTime != 0L) {
                Thread.sleep(1000)
                leftTime -= 1000

                // 남은 시간 -> 시간, 분, 초로 변환
                sec = (leftTime / 1000).toInt()
                min = sec / 60
                sec = sec % 60
                hour = min / 60

                handler.post {
                    // 텍스트 띄우기
                    str_hour = String.format("%02d", hour)
                    str_min = String.format("%02d", min)
                    str_sec = String.format("%02d", sec)
                    binding.tvTime.text = str_hour + ":" + str_min + ":" + str_sec
                }
            }


            handler.post {
                // 사운드 재생
                sp.play(sound, 1F, 1F, 0, 1, 1F)
                // 토스트 메시지
                Toast.makeText(this, "종료!!!!!!", LENGTH_SHORT).show()
            }

        }.start()


        // 취소 버튼
        binding.btnCancel.setOnClickListener {
            // 액티비티 이동
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}

