package com.masai.nalini.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.nalini.R
import com.masai.nalini.local.WishListData
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.local.wishListDao
import com.masai.nalini.remote.data.ApiService
import com.masai.nalini.remote.data.Netwark
import com.masai.nalini.remote.model.datamodel.Data
import com.masai.nalini.repository.DataRepository
import com.masai.nalini.ui.adapter.Adapter
import com.masai.nalini.ui.adapter.listner.OnClickAddToWatchList
import com.masai.nalini.ui.fragment.Market
import com.masai.nalini.viewmodel.MainViewModel
import com.masai.nalini.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_search2.*
import kotlinx.android.synthetic.main.fragment_a_all.*

class SearchActivity2 : AppCompatActivity() ,OnClickAddToWatchList{
    lateinit var viewModel2: MainViewModel
    lateinit var wishListDao: wishListDao
    lateinit var repository: DataRepository

    lateinit var adapter2: Adapter
    private var List = mutableListOf<Data>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search2)
        imBackToMarket.setOnClickListener {
            startActivity(Intent(this,FragmentActivity::class.java))
        }

        wishListDao= WishListData.getWishListDatabase(this).getWishList()
        val userApi= Netwark.getInstance().create(ApiService::class.java)
        repository= DataRepository(wishListDao,userApi)
        val wishlistFactory= ViewModelFactory(repository)
        viewModel2= ViewModelProviders.of(this,wishlistFactory).get(MainViewModel::class.java)

        setRecycle()
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) {
                filterCollection(s.toString())

            }
        })

        Log.d("getdata","response")
        viewModel2.user.observe(this, Observer {
            Log.d("getdata","response")
            List.clear()
            List.addAll(it.data as MutableList<Data>)
            adapter2.notifyDataSetChanged()
        })
    }
    private fun filterCollection(currency: String) {
        //val list: ArrayList<Data> = ArrayList<view>()
        var list= mutableListOf<Data>()
        for (name in List) {
            if ( name.name.toLowerCase().contains(currency.toLowerCase())){
                list.add(name)
            }
        }
        if (list.isEmpty()){
            val toast= Toast.makeText(this,"No currency found", Toast.LENGTH_LONG)
            toast.show()
        }else{
            adapter2.FILTER(list)
        }
    }
    fun setRecycle(){
        adapter2 = Adapter(List,this,this)
        recycleSearch.adapter = adapter2
        recycleSearch.layoutManager = LinearLayoutManager(this)
    }

    override fun AddToWatchList(data: Data) {
        val wishlistEntity= WishlistEntity(data.name,data.id,data.symbol,data.quote.uSD.price,data.quote.uSD.percentChange24h)
        viewModel2.CreateWishList(wishlistEntity)
    }
}