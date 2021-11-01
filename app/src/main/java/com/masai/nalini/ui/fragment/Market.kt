package com.masai.nalini.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.masai.nalini.R
import com.masai.nalini.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_market.*


class Market : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_market, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       var ViewPagerAdapter= fragmentManager?.let { ViewPagerAdapter(it,lifecycle) }
        viewPager.setAdapter(ViewPagerAdapter)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position == 0) {
                tab.text = "   All               "
            } else if (position == 1) {
                tab.text = "WatchList"
            } else if (position == 2) {
                tab.text = "Top Gainers"
            }else if (position==3){
                tab.text="Top Losers"
            }
        }.attach()
    }
}