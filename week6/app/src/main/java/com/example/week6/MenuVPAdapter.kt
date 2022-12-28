package com.example.week6

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MenuVPAdapter(fragmentActivity: MenuFragment): FragmentStateAdapter(fragmentActivity) {
    // 총 아이템의 개수
    override fun getItemCount(): Int = 3

    // 포지션에 따라 어떤 프래그먼트를 보여줄지 설정
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OneFragment()
            1 -> TwoFragment()
            2 -> ThreeFragment()
            else -> OneFragment()
        }
    }
}