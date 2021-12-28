package com.masai.nalini.ui.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.INotificationSideChannel
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
import com.masai.nalini.viewmodel.MainViewModel
import com.masai.nalini.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_buy_now2.*
import kotlinx.android.synthetic.main.activity_sell2.*
import kotlinx.coroutines.InternalCoroutinesApi

class SellActivity2 : AppCompatActivity() {
    @InternalCoroutinesApi
    lateinit var viewModel2: MainViewModel
    lateinit var wishListDao: wishListDao
    lateinit var repository: DataRepository
    lateinit var transactionDao: TransactionDao
    var sell:Int=0
    var a:Int=0
    var ammount1:Int=0
    lateinit var adapter2: Adapter
    private var List = mutableListOf<Data>()
    private var List2= mutableListOf<TransactionEntity>()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell2)
        transactionDao= WishListData.getWishListDatabase(this).getTransaction()



        wishListDao= WishListData.getWishListDatabase( this).getWishList()
        val userApi= Netwark.getInstance().create(ApiService::class.java)
        repository= DataRepository(transactionDao,wishListDao,userApi)
        val wishlistFactory= ViewModelFactory(repository)
        viewModel2= ViewModelProviders.of(this,wishlistFactory).get(MainViewModel::class.java)

        val intent=intent
        val per=intent.getDoubleExtra("per",0.0)
        val name =intent.getStringExtra("name")
        val price=intent.getDoubleExtra("price",0.0)
        val change=intent.getDoubleExtra("change",0.0)
        val oneMonth=intent.getDoubleExtra("monthhigh",0.0)
        val twoMonth=intent.getDoubleExtra("teomonth",0.0)
        val symbol=intent.getStringExtra("symbol")
        val id=intent.getIntExtra("id",0)
        val name1=name.toString()
        val symbol2=symbol.toString()
        viewModel2.getAllTransaction().observe(this, Observer {
            //  Toast.makeText(this,it.amount.toString(),Toast.LENGTH_SHORT).show()
//if (it.size>0){
//    tvAvailableSell.setText("$"+it[0].amount.toString()+".00Available ")
//    etSellNow.setText(it[1].amount.toString())
//}
            List2.addAll(it)
            var a=0
List2.forEach{
    if (it.id==id){
        etSellNow.setText(it.amount.toString())
        tvAvailableSell.setText("$${it.amount} ${it.name} Available for sale")
a++
    }
}
            if (a==0){
                tvAvailableSell.setText("No  $name Available for sale")
                tvAvailableSell.setTextColor(Color.RED)

            }


        })

        btnSellNow.setOnClickListener {
            val r =TransactionEntity(per,ammount1,name1,id,symbol2,price,change)
            viewModel2.DeleteTransaction(r)
            val intent=Intent(this,FragmentActivity::class.java)
            startActivity(intent)







        }
    }
}