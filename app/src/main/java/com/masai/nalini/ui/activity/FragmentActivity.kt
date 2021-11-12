package com.masai.nalini.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.masai.nalini.R
import com.masai.nalini.ui.fragment.*
import kotlinx.android.synthetic.main.activity_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        CoroutineScope(Dispatchers.IO).launch {
            //fragmentScreens()
            bottomNavigation()
        }

    }
    private fun bottomNavigation() {
        bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            var temp: Fragment? = null
            when (item.itemId) {
                R.id.menuHome -> temp = Home()
                R.id.menuProfile -> temp = Profile()
                R.id.menuRewards -> temp = Rewards()
                R.id.menuPortfolio -> temp = Portfolio()
                R.id.menuMarket->temp=Market()
            }
            if (temp != null) {
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, temp).commit()
            }
            true
        })
    }





}