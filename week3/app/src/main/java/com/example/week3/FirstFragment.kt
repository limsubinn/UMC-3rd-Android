package com.example.week3;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.week3.databinding.FragmentFirstBinding

class FirstFragment: Fragment() {
    private lateinit var viewBinding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentFirstBinding.inflate(layoutInflater)

        // 버튼 클릭 리스너
        viewBinding.button3.setOnClickListener {
            // text3의 텍스트 값을 result에 저장
            val result = viewBinding.text3.text.toString()
            // requestKey에 bundleKey(result) set
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        }

        return viewBinding.root
    }

}
