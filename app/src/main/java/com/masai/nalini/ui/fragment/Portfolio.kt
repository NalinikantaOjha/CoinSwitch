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
import com.masai.nalini.local.transaction.TransactionDao
import com.masai.nalini.local.transaction.TransactionEntity
import com.masai.nalini.local.wishListDao
import com.masai.nalini.remote.data.ApiService
import com.masai.nalini.remote.data.Netwark
import com.masai.nalini.remote.model.datamodel.Data
import com.masai.nalini.repository.DataRepository
import com.masai.nalini.ui.adapter.Adapter
import com.masai.nalini.ui.adapter.TransAdapter
import com.masai.nalini.ui.adapter.listner.OnClickAddToWatchList
import com.masai.nalini.ui.adapter.listner.OnTransctionListner
import com.masai.nalini.viewmodel.MainViewModel
import com.masai.nalini.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_a_all.*
import kotlinx.android.synthetic.main.fragment_portfolio.*
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.DecimalFormat


class Portfolio : Fragment() ,OnTransctionListner,OnClickAddToWatchList{
    lateinit var viewModel2: MainViewModel
    lateinit var wishListDao: wishListDao
    lateinit var repository: DataRepository
    lateinit var transactionDao: TransactionDao
    lateinit var adapter: Adapter
    private var List2 = mutableListOf<Data>()
    val dec = DecimalFormat("#.##")
    var investedValue:Int=0
    var Worth:Double=0.0
    var profit:Double=0.0
    var a:Int=0

    lateinit var adapter2: TransAdapter
    private var List = mutableListOf<TransactionEntity>()
private var listofData= mutableListOf<Int>()
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
        return inflater.inflate(R.layout.fragment_portfolio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wishListDao= WishListData.getWishListDatabase(context as Activity).getWishList()
        val userApi= Netwark.getInstance().create(ApiService::class.java)
        transactionDao= WishListData.getWishListDatabase(context as Activity).getTransaction()
        repository= DataRepository(transactionDao,wishListDao,userApi)
        val wishlistFactory= ViewModelFactory(repository)
        viewModel2= ViewModelProviders.of(this,wishlistFactory).get(MainViewModel::class.java)
        viewModel2.getAllTransaction().observe(viewLifecycleOwner, Observer {
            List.clear()
            List.addAll(it)
            setRecycle()
            val data=it
            investedValue=0
            data.forEach {
                investedValue+=it.amount
                listofData.add(it.id)
            }
            data.forEach {
                Worth+=it.price
            }

            val number = java.lang.Double.valueOf(Worth)
            val credits = dec.format(number)
            tvWORTH.setText("$ "+credits)
            tvInvestedValue.setText("$"+investedValue.toString())

        })
        adapter= Adapter(List2,this,context as Activity)
        viewModel2.user.observe(viewLifecycleOwner, Observer {
            Log.d("getdata","response")
            List2.clear()
            List2.addAll(it.data as MutableList<Data>)
            adapter.notifyDataSetChanged()
        })
        List.forEach {
            var a=0
            if (it.id==listofData[a]){

            }
            a++
        }
        List2.forEach {
            a=0
            if (it.id==listofData[a]){
                profit+=it.quote.uSD.price
            }
            a++
        }
       // tvLossOrGain.setText(profit.toString())


    }
    fun setRecycle(){
        adapter2 = TransAdapter(List,this,context as Activity)
      stocksRecycle.adapter = adapter2
       stocksRecycle.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun OnTrans(transactionEntity: TransactionEntity) {

    }

    override fun AddToWatchList(data: Data) {
    }
}