package com.masai.nalini.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
import kotlinx.android.synthetic.main.fragment_portfolio.*
import kotlinx.coroutines.InternalCoroutinesApi
import java.text.DecimalFormat
import java.util.*


class Portfolio : Fragment(), OnTransctionListner, OnClickAddToWatchList {
    @InternalCoroutinesApi
    lateinit var viewModel2: MainViewModel
    lateinit var wishListDao: wishListDao
    lateinit var repository: DataRepository
    lateinit var transactionDao: TransactionDao
    lateinit var adapter: Adapter
    private var List2 = mutableListOf<Data>()

    val dec = DecimalFormat("#.##")
    var investedValue: Int = 0
    var Worth: Double = 0.0
    var WorthInvested: Double = 0.0

    lateinit var adapter2: TransAdapter
    private var List = mutableListOf<TransactionEntity>()
    private var listofData = mutableListOf<Int>()
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

    @SuppressLint("SetTextI18n")
    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wishListDao = WishListData.getWishListDatabase(context as Activity).getWishList()
        val userApi = Netwark.getInstance().create(ApiService::class.java)
        transactionDao = WishListData.getWishListDatabase(context as Activity).getTransaction()
        repository = DataRepository(transactionDao, wishListDao, userApi)
        val wishlistFactory = ViewModelFactory(repository)
        viewModel2 = ViewModelProviders.of(this, wishlistFactory).get(MainViewModel::class.java)


        viewModel2.getAllTransaction().observe(viewLifecycleOwner, {
            List.clear()
            List.addAll(it)
            setRecycle()
            val data = it
            investedValue = 0
            data.forEach {
                investedValue += it.amount
                //Adding the id of currency in the table to list of Data
                listofData.add(it.id)
            }
//Finding total invested price
            data.forEach {
                WorthInvested += ((it.price / 100) * it.per)
                Worth += it.price
            }
//Observing real time data and adding to List2
            viewModel2.dataLive().observe(viewLifecycleOwner, {
                Log.d("getdata", "response")
                List2.clear()
                List2.addAll(it.data as MutableList<Data>)
                //Declaration of variable to add total realtime value of invested amount
                var v = 0.0
                // sorting the list that contains id of the table
                Collections.sort(listofData)
                List2.sortByDescending {
                    it.id
                }
                //Adding the real price of the amount invested
                var e = 0
                while (e < listofData.size - 1) {
                    List2.forEach {
                        if (it.id === listofData.get(e)) {
                            val per = List[e].per
                            v = v + ((it.quote.uSD.price / 100) * per)
                            e++
                        }
                    }
                }
                // Adding the real value of last id in the table
                if (listofData.size > 0) {
                    List2.forEach {
                        if (it.id === listofData.get(listofData.size - 1)) {
                            val per = List[listofData.size - 1].per
                            v = v + ((it.quote.uSD.price / 100) * per)
                        }
                    }
                }
//Total value invested
                val number3 = java.lang.Double.valueOf(WorthInvested)
                //The Total real time value of invested amount
                val number2 = java.lang.Double.valueOf(v)
                tvInvestedValue.setText("$$investedValue")
                //Total profit and loss
                val profit = number2 - number3
                val credits2 = dec.format(profit)
                tvTotalGainAndLoss.setText(credits2.toString())
                //Set Colour for total gain and loss textView
                if (profit >= 0) {
                    tvTotalGainAndLoss.setTextColor(Color.GREEN)

                } else {
                    tvTotalGainAndLoss.setTextColor(Color.RED)
                }
                //Set the total realtime value of invested amount
                val credits5 = dec.format(number2)
                tvWORTH.setText("$ " + credits5)
                tvInvestedValue.setText("$" + investedValue.toString())
            }
            )

        })
    }

    fun setRecycle() {
        adapter2 = TransAdapter(List, this, context as Activity)
        stocksRecycle.adapter = adapter2
        stocksRecycle.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun OnTrans(transactionEntity: TransactionEntity) {

    }

    override fun AddToWatchList(data: Data) {
    }
}