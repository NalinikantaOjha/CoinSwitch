package com.masai.nalini.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.masai.nalini.R
import com.masai.nalini.local.WishListData
import com.masai.nalini.local.transaction.TransactionDao
import com.masai.nalini.local.wishListDao
import com.masai.nalini.remote.data.ApiService
import com.masai.nalini.remote.data.Netwark
import com.masai.nalini.remote.model.datamodel.Data
import com.masai.nalini.repository.DataRepository
import com.masai.nalini.ui.activity.SearchActivity2
import com.masai.nalini.ui.adapter.Adapter
import com.masai.nalini.ui.adapter.ViewPagerAdapter
import com.masai.nalini.viewmodel.MainViewModel
import com.masai.nalini.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_market.*


class Market : Fragment() {
    lateinit var viewModel2: MainViewModel
    lateinit var wishListDao: wishListDao
    lateinit var repository: DataRepository
lateinit var transactionDao:TransactionDao
    lateinit var adapter2: Adapter
    private var List = mutableListOf<Data>()

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
        wishListDao= WishListData.getWishListDatabase(context as Activity).getWishList()
        transactionDao=WishListData.getWishListDatabase(context as Activity).getTransaction()
        val userApi= Netwark.getInstance().create(ApiService::class.java)
        repository= DataRepository(transactionDao,wishListDao,userApi)
        val wishlistFactory= ViewModelFactory(repository)
        viewModel2= ViewModelProviders.of(this,wishlistFactory).get(MainViewModel::class.java)



        Log.d("getdata","response")
        viewModel2.user.observe(viewLifecycleOwner, Observer {
            Log.d("getdata","response")


//            List.clear()
//            List.addAll(it.data as MutableList<Data>)
//            adapter2.notifyDataSetChanged()
//
        })
imSearch.setOnClickListener {
    startActivity(Intent(this.context,SearchActivity2::class.java))
}

        var ViewPagerAdapter= fragmentManager?.let { ViewPagerAdapter(it,lifecycle) }
        viewPager.setAdapter(ViewPagerAdapter)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position == 0) {
                tab.text = "   All                  "
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