package com.example.week3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.week3.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // fragment로부터 결과 받기 (requestKey를 통해 bundleKey(result) 수신)
        supportFragmentManager
            .setFragmentResultListener("requestKey", this) { requestKey, bundle ->
                val result = bundle.getString("bundleKey")
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }

        // Fragment 객체 생성
        var firstFragment =  FirstFragment()
        var secondFragment = SecondFragment()

        // 첫 번째 Fragment를 Container에 할당
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.fragment.id, firstFragment)
            .commitAllowingStateLoss()

        // 버튼 클릭 리스너 -> 첫 번째 Fragment를 Container에 할당
        viewBinding.btn1.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.fragment.id, firstFragment)
                .commitAllowingStateLoss()
        }

        // 버튼 클릭 리스너 -> 두 번째 Fragment를 Container에 할당
        viewBinding.btn2.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.fragment.id, secondFragment)
                .commitAllowingStateLoss()
        }
    }
}
