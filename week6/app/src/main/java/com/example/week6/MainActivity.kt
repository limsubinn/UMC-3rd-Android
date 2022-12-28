package com.example.week6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(binding.containerFragment.id, HomeFragment())
            .commitAllowingStateLoss()

        // run -> 범위 지정 함수
        binding.navBottom.run {
            setOnItemSelectedListener {
                // 사용자의 클릭에 따라 해당 프래그먼트가 화면에 보여진다.
                when (it.itemId) { // switch문
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_menu -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, MenuFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_setting -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, SettingFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true // 리스너의 리턴값 설정
            }
            // 처음 실행했을 때 자동으로 menu_home에 해당하는 아이템을 가리킨다.
            selectedItemId = R.id.menu_home
        }
    }
}