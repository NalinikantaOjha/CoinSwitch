package com.masai.nalini.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.masai.nalini.R
import com.masai.nalini.remote.model.datamodel.Data
import com.masai.nalini.ui.adapter.listner.OnClickAddToWatchList
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.DecimalFormat

class DataViewHolder(itemView:View,val onClickAddToWatchList: OnClickAddToWatchList):RecyclerView.ViewHolder(itemView) {
    val dec = DecimalFormat("#.##")
    //val number = java.lang.Double.valueOf(interestValue)
   // val credits = dec.format(number)
    fun setData(dataDTO: Data){
        itemView.apply {
            cardView.setOnClickListener {
                onClickAddToWatchList.AddToWatchList(dataDTO)
            }
            if(dataDTO.name.equals("Bitcoin")){
                imageurl.setImageResource(R.drawable.bitcoin)
            }else if (dataDTO.name.equals("Ethereum")){
                imageurl.setImageResource(R.drawable.therum)
            }else if (dataDTO.name.equals("Tether")){
                imageurl.setImageResource(R.drawable.thther)
            }else if (dataDTO.name.equals("Solana")){
                imageurl.setImageResource(R.drawable.solana)
            }
            Log.d("recycle","nalini")
            tvName.setText(dataDTO.name.toString())
            tvLogo.setText(dataDTO.symbol.toString())
           // tvChange.setText("$"+dec.format( dataDTO.quote?.uSD?.percentChange24h.toString()))
          // tvPrice.setText("₹"+dataDTO.quote?.uSD?.price.toString())
            val number2=java.lang.Double.valueOf(dataDTO.quote?.uSD.percentChange24h)
            val credits2=dec.format(number2)
            if (dataDTO.quote.uSD.percentChange24h>0){
                tvChange.setTextColor(Color.GREEN)
            }else{
                tvChange.setTextColor(Color.RED)
            }
            tvChange.setText("% "+credits2)
            val number = java.lang.Double.valueOf(dataDTO.quote?.uSD.price)
            val credits = dec.format(number)
            tvPrice.setText("$ "+credits)

        }

    }
}