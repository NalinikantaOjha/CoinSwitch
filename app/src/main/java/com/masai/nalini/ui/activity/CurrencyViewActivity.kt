package com.masai.nalini.ui.activity

import android.graphics.Color
import android.icu.text.RelativeDateTimeFormatter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.masai.nalini.R
import com.masai.nalini.local.WishListData
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.local.wishListDao
import com.masai.nalini.remote.data.ApiService
import com.masai.nalini.remote.data.Netwark
import com.masai.nalini.remote.model.datamodel.Data
import com.masai.nalini.repository.DataRepository
import com.masai.nalini.ui.adapter.Adapter
import com.masai.nalini.viewmodel.MainViewModel
import com.masai.nalini.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_currency_view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.DecimalFormat

class CurrencyViewActivity : AppCompatActivity() {
    lateinit var viewModel2: MainViewModel
    lateinit var wishListDao: wishListDao
    lateinit var repository: DataRepository
    lateinit var adapter2: Adapter
    private var List = mutableListOf<Data>()
    val dec = DecimalFormat("#.##")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_view)
        wishListDao= WishListData.getWishListDatabase(this).getWishList()
        val userApi= Netwark.getInstance().create(ApiService::class.java)
        repository= DataRepository(wishListDao,userApi)
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

            tvChangeCV.setTextColor(Color.BLUE)
        }else{
            tvChangeCV.setTextColor(Color.RED)
        }
        val wishListEntity=WishlistEntity(name.toString(),id,symbol.toString(),price,change)
        addToWatchList.setOnClickListener {

            //val wishlistEntity=WishlistEntity(data.name,data.id,data.symbol,data.quote.uSD.price,data.quote.uSD.percentChange24h)
            viewModel2.CreateWishList(wishListEntity)
        }
    }
}