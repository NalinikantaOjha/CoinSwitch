package com.masai.nalini.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.masai.nalini.R
import com.masai.nalini.ui.fragment.*
import kotlinx.android.synthetic.main.activity_fragment.*

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
fragmentScreens()
    }


       @SuppressLint("NonConstantResourceId")
       fun fragmentScreens () {
           supportFragmentManager.beginTransaction().replace(R.id.mainframe, Home())
               .commit()

          bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
               var temp: Fragment? = null
               when (item.itemId) {
                   R.id.menuHome -> temp = Home()
                   R.id.menuPortfolio -> temp = Portfolio()
                   R.id.menuRewards -> temp = Rewards()
                   R.id.menuMarket -> temp = Market()
                   R.id.menuProfile->temp=Profile()
               }
               assert(temp != null)
               supportFragmentManager.beginTransaction().replace(R.id.mainframe, temp!!).commit()
               true
           })
       }



}