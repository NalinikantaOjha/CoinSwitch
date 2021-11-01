package com.masai.nalini.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.masai.nalini.ui.fragment.*

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return All()
            1 -> return Watchlist()
            2 -> return TopGainers()
            3 -> return TopLosers()

        }

       return null!!
    }


    override fun getItemCount(): Int {
        return 4
    }



}
