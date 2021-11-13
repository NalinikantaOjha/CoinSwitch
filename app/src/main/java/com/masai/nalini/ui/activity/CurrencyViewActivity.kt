package com.masai.nalini.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.icu.text.RelativeDateTimeFormatter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.masai.nalini.R
import com.masai.nalini.local.WishListData
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.local.transaction.TransactionDao
import com.masai.nalini.local.wishListDao
import com.masai.nalini.remote.data.ApiService
import com.masai.nalini.remote.data.Netwark
import com.masai.nalini.remote.model.datamodel.Data
import com.masai.nalini.repository.DataRepository
import com.masai.nalini.ui.adapter.Adapter
import com.masai.nalini.viewmodel.MainViewModel
import com.masai.nalini.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_currency_view.*
import kotlinx.android.synthetic.main.activity_sell2.*
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.DecimalFormat

class CurrencyViewActivity : AppCompatActivity() {
    lateinit var viewModel2: MainViewModel
    lateinit var wishListDao: wishListDao
    lateinit var repository: DataRepository
    lateinit var adapter2: Adapter
    lateinit var transactionDao:TransactionDao

    private var List = mutableListOf<Data>()
    val dec = DecimalFormat("#.##")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_view)
        wishListDao= WishListData.getWishListDatabase(this).getWishList()
        val userApi= Netwark.getInstance().create(ApiService::class.java)
        transactionDao=WishListData.getWishListDatabase(this).getTransaction()
        repository= DataRepository(transactionDao,wishListDao,userApi)
        val wishlistFactory= ViewModelFactory(repository)
        viewModel2= ViewModelProviders.of(this,wishlistFactory).get(MainViewModel::class.java)

        val intent=intent
        val name =intent.getStringExtra("name")
        val price=intent.getDoubleExtra("price",0.0)
        val change=intent.getDoubleExtra("change",0.0)
        val oneMonth=intent.getDoubleExtra("monthhigh",0.0)
        val twoMonth=intent.getDoubleExtra("teomonth",0.0)
        val symbol=intent.getStringExtra("symbol")
        val id=intent.getIntExtra("id",0)
        tvNameCV.setText(name)
        val number2=java.lang.Double.valueOf(change)
        val credits2=dec.format(number2)
        tvChangeCV.setText(credits2+"%")
        val number = java.lang.Double.valueOf(price)
        val credits = dec.format(number)
        tvPriceCV.setText("$ "+credits)
        val number3=java.lang.Double.valueOf(oneMonth)
        val credits3=dec.format(number3)
        tvHigh.setText("$"+credits3)
        val number4=java.lang.Double.valueOf(twoMonth)
        val credits5=dec.format(number4)
        tvLow.setText(credits5)

        if (change>0){
ViewChart.setImageResource(R.drawable.posetivechart)
            tvChangeCV.setTextColor(Color.BLUE)
        }else{
            ViewChart.setImageResource(R.drawable.nagativechart)
            tvChangeCV.setTextColor(Color.RED)
        }
        if(name.equals("Bitcoin")){
            setLogo.setImageResource(R.drawable.bitcoin)
        }else if (name.equals("Ethereum")){
            setLogo.setImageResource(R.drawable.therum)
        }else if (name.equals("Tether")){
            setLogo.setImageResource(R.drawable.thther)
        }else if (name.equals("Solana")){
            setLogo.setImageResource(R.drawable.solana)
        }

        //watch list
        val wishListEntity=WishlistEntity(name.toString(),id,symbol.toString(),price,change)
        viewModel2.IsAvalable(id).observe(this, Observer {
            if (it.size>0){
               // Toast.makeText(this,it[0].id.toString(),Toast.LENGTH_LONG).show()
                addToWatchList.setImageResource(R.drawable.ic_baseline_stars_25)
            }
        })

        addToWatchList.setOnClickListener {


            viewModel2.IsAvalable(id).observe(this, Observer {

                if (it.size==0){
                    viewModel2.CreateWishList(wishListEntity)
                    addToWatchList.setImageResource(R.drawable.ic_baseline_stars_25)
                }

            })

        }

        //buy
btnBuy.setOnClickListener {
    val intent= Intent(this,BuyNowActivity2::class.java)
    intent.putExtra("name",name)
    intent.putExtra("price",price)
    intent.putExtra("change",change)
    intent.putExtra("monthhigh",oneMonth)
    intent.putExtra("teomonth",twoMonth)
    intent.putExtra("id",id)
    intent.putExtra("symbol",symbol)
    startActivity(intent)
}
        btnSell.setOnClickListener {
            val intent= Intent(this,SellActivity2::class.java)
            intent.putExtra("name",name)
            intent.putExtra("price",price)
            intent.putExtra("change",change)
            intent.putExtra("monthhigh",oneMonth)
            intent.putExtra("teomonth",twoMonth)
            intent.putExtra("id",id)
            intent.putExtra("symbol",symbol)
            startActivity(intent)
        }

    }
}