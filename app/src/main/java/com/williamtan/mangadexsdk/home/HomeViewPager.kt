package com.williamtan.mangadexsdk.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPager(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val screenTypeList = mutableListOf<HomeScreenType>()

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }

}

enum class HomeScreenType {
    MangaList
}