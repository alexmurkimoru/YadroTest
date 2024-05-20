package com.example.yadrotest

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter(fragment: Fragment, val dataSource: DataSource?) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PagerFirstFragment.newInstance(dataSource!!)
            1 -> PagerSecondFragment.newInstance(dataSource!!)
            else -> null!!
        }
    }
}