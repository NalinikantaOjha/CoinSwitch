package com.masai.nalini.ui.adapter

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.masai.nalini.R
import com.masai.nalini.local.WishListData
import com.masai.nalini.local.transaction.TransactionDao
import com.masai.nalini.local.transaction.TransactionEntity
import com.masai.nalini.local.wishListDao
import com.masai.nalini.remote.data.ApiService
import com.masai.nalini.remote.data.Netwark
import com.masai.nalini.remote.model.datamodel.Data
import com.masai.nalini.remote.model.datamodel.ModelDto
import com.masai.nalini.repository.DataRepository
import com.masai.nalini.ui.adapter.listner.OnTransctionListner
import com.masai.nalini.viewmodel.MainViewModel
import com.masai.nalini.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.coroutines.InternalCoroutinesApi
import java.text.DecimalFormat

class TransactionViewHolder(itemView:View,val onTransctionListner: OnTransctionListner):RecyclerView.ViewHolder(itemView) {

    val dec = DecimalFormat("#.##")



    fun setDataTrans(tranas:TransactionEntity){

        itemView.apply {
            cardView.setOnClickListener {
                onTransctionListner.OnTrans(tranas)
            }

            if(tranas.name.equals("Bitcoin")){
                imageurl.setImageResource(R.drawable.bitcoin)
            }else if (tranas.name.equals("Ethereum")){
                imageurl.setImageResource(R.drawable.therum)
            }else if (tranas.name.equals("Tether")){
                imageurl.setImageResource(R.drawable.thther)
            }else if (tranas.name.equals("Solana")){
                imageurl.setImageResource(R.drawable.solana)
            }
            Log.d("recycle","nalini")
            tvName.setText(tranas.name.toString())

            // tvChange.setText("$"+dec.format( dataDTO.quote?.uSD?.percentChange24h.toString()))
            // tvPrice.setText("₹"+dataDTO.quote?.uSD?.price.toString())
            var number3=(tranas.price / 100)
            val number4 = java.lang.Double.valueOf(number3)
            val credits4 = dec.format(number4)
            tvLogo.setText(credits4.toString()+tranas.symbol.toString())
            val number2=java.lang.Double.valueOf(tranas.change)
            val credits2=dec.format(number2)

                tvChange.setTextColor(Color.RED)

            tvChange.setText("-> ")
            val number = java.lang.Double.valueOf(tranas.amount.toDouble())
            val credits = dec.format(number)
            tvPrice.setText("$ "+credits)

        }

    }
}