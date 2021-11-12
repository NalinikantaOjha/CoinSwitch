package com.masai.nalini.ui.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.nalini.R
import com.masai.nalini.local.WishListData
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.local.wishListDao
import com.masai.nalini.remote.data.ApiCall
import com.masai.nalini.remote.data.ApiService
import com.masai.nalini.remote.data.Netwark
import com.masai.nalini.remote.data.Network
import com.masai.nalini.remote.model.datamodel.Data
import com.masai.nalini.repository.DataRepository
import com.masai.nalini.ui.adapter.Adapter
import com.masai.nalini.ui.adapter.WishListAdapter
import com.masai.nalini.ui.adapter.listner.OnClickDeleteWishList
import com.masai.nalini.viewmodel.MainViewModel
import com.masai.nalini.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_top_losers.*
import kotlinx.android.synthetic.main.fragment_watchlist.*


class Watchlist : Fragment(),OnClickDeleteWishList {
    lateinit var mainViewModel: MainViewModel
    lateinit var repository: DataRepository
    lateinit var wishListDao: wishListDao

    lateinit var adapter2: WishListAdapter
    private var List = mutableListOf<WishlistEntity>()


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
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiCll= Netwark.getInstance().create(ApiCall::class.java)

        adapter2 = WishListAdapter(List,this,context as Activity)
        recycleWishlist.adapter = adapter2

        recycleWishlist.layoutManager = LinearLayoutManager(requireContext())

        wishListDao=WishListData.getWishListDatabase(context as Activity).getWishList()
        val userApi= Netwark.getInstance().create(ApiService::class.java)
        repository= DataRepository(wishListDao,userApi)
        val wishlistFactory= ViewModelFactory(repository)
        mainViewModel=ViewModelProviders.of(this,wishlistFactory).get(MainViewModel::class.java)
        setRecycle()
  mainViewModel.getWishList().observe(viewLifecycleOwner, Observer {
List.clear()
      List.addAll(it)
      adapter2.notifyDataSetChanged()


  })

    }
    fun setRecycle(){
        adapter2 = WishListAdapter(List,this,context as Activity)
        recycleWishlist.adapter = adapter2
        recycleWishlist.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun OnDelteWatch(wishlistEntity: WishlistEntity){
        //val wishlistEntity=WishlistEntity(data.name,data.id,data.symbol,data.quote.uSD.price,data.quote.uSD.percentChange24h)
        mainViewModel.wishListDelete(wishlistEntity)
    }
    }