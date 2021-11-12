package com.masai.nalini.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.nalini.R
import com.masai.nalini.local.WishListData
import com.masai.nalini.local.wishListDao
import com.masai.nalini.remote.data.ApiService
import com.masai.nalini.remote.data.Netwark
import com.masai.nalini.remote.model.datamodel.Data
import com.masai.nalini.repository.DataRepository
import com.masai.nalini.ui.adapter.Adapter
import com.masai.nalini.ui.adapter.listner.OnClickAddToWatchList
import com.masai.nalini.viewmodel.MainViewModel
import com.masai.nalini.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_a_all.*
import kotlinx.android.synthetic.main.fragment_top_gainers.*
import java.util.*


class TopGainers : Fragment(),OnClickAddToWatchList {

    lateinit var viewModel2: MainViewModel
    lateinit var wishListDao: wishListDao
    lateinit var repository: DataRepository

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
        return inflater.inflate(R.layout.fragment_top_gainers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wishListDao= WishListData.getWishListDatabase(context as Activity).getWishList()
        val userApi= Netwark.getInstance().create(ApiService::class.java)
        repository= DataRepository(wishListDao,userApi)
        val wishlistFactory= ViewModelFactory(repository)
        viewModel2= ViewModelProviders.of(this,wishlistFactory).get(MainViewModel::class.java)

        viewModel2.user.observe(viewLifecycleOwner, Observer {
            Log.d("getdata","response")
            List.clear()
            List.addAll(it.data as MutableList<Data>)
            List.sortByDescending {
                it.quote.uSD.percentChange24h
            }
            adapter2.notifyDataSetChanged()
        })

        setRecycle()
    }
    fun setRecycle(){
        adapter2 = Adapter(List,this,context as Activity)
        recycleTopGainers.adapter = adapter2
        recycleTopGainers.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun AddToWatchList(data: Data) {

    }
}