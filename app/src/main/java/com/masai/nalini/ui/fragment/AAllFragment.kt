package com.masai.nalini.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.masai.nalini.remote.model.datamodel.ModelDto
import com.masai.nalini.repository.DataRepository
import com.masai.nalini.ui.activity.CurrencyViewActivity
import com.masai.nalini.ui.adapter.Adapter
import com.masai.nalini.ui.adapter.listner.OnClickAddToWatchList
import com.masai.nalini.viewmodel.MainViewModel
import com.masai.nalini.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_a_all.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class AAllFragment : Fragment(),OnClickAddToWatchList {
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
        return inflater.inflate(R.layout.fragment_a_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wishListDao=WishListData.getWishListDatabase(context as Activity).getWishList()
        val userApi= Netwark.getInstance().create(ApiService::class.java)
        repository= DataRepository(wishListDao,userApi)
        val wishlistFactory=ViewModelFactory(repository)
        viewModel2=ViewModelProviders.of(this,wishlistFactory).get(MainViewModel::class.java)

        setRecycle()

        Log.d("getdata","response")
        viewModel2.user.observe(viewLifecycleOwner, Observer {
            Log.d("getdata","response")
            List.clear()
            List.addAll(it.data as MutableList<Data>)
            adapter2.notifyDataSetChanged()
        })
        //viewModel2.CreateWishList()

    }


    private fun callApi2() {


        val apiCall= Network.getRetrofitInstance().create(ApiCall::class.java)
        apiCall.getUserDetails2("de3fcad6-161b-4dfc-9fa1-889c99422601").enqueue(object : Callback<ModelDto> {
            override fun onResponse(call: Call<ModelDto>, response: Response<ModelDto>) {
                Log.d("recycle1", response.body()?.status?.totalCount.toString())
                Log.d("recycle",List.size.toString())
                response.body()!!.data.let {

                    List= it as MutableList<Data>
                  // setRecycle()


                }

            }

            override fun onFailure(call: Call<ModelDto>, t: Throwable) {
                Log.d("recycle","${t.message}")
            }

        })



    }
    fun setRecycle(){
        adapter2 = Adapter(List,this,context as Activity)
        recycleAll.adapter = adapter2
        recycleAll.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun AddToWatchList(data: Data) {

       val intent=Intent(this.context,CurrencyViewActivity::class.java)
        intent.putExtra("name",data.name)
        intent.putExtra("price",data.quote.uSD.price)
        intent.putExtra("change",data.quote.uSD.percentChange24h)
        intent.putExtra("monthhigh",data.quote.uSD.percentChange30d)
        intent.putExtra("teomonth",data.quote.uSD.percentChange60d)
        intent.putExtra("id",data.id)
        intent.putExtra("symbol",data.symbol)
        startActivity(intent)
    }
}


