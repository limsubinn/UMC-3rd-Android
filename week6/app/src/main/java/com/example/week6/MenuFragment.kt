package com.example.week6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week6.databinding.FragmentMenuBinding
import com.google.android.material.tabs.TabLayoutMediator

class MenuFragment: Fragment() {
    private val binding:FragmentMenuBinding by lazy {
        FragmentMenuBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val menuVPAdapter = MenuVPAdapter(this)
        binding.vpMenu.adapter = menuVPAdapter

        val tabTitleArray = arrayOf(
            "One",
            "Two",
            "Three"
        )

        TabLayoutMediator(binding.tabMenu, binding.vpMenu) { tab, position ->
            // 탭의 포지션에 따라 탭의 타이틀 설정
            tab.text = tabTitleArray[position]
        }.attach()

        return binding.root
    }
}