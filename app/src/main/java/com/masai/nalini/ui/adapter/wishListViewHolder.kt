package com.masai.nalini.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.masai.nalini.local.WishlistEntity
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.DecimalFormat

class wishListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    val dec = DecimalFormat("#.##")
    fun setData(wishlistEntity: WishlistEntity){
        itemView.apply {
            Log.d("recycle","nalini")
            tvName.setText(wishlistEntity.name.toString())
            tvLogo.setText(wishlistEntity.symbol.toString())
            // tvChange.setText("$"+dec.format( dataDTO.quote?.uSD?.percentChange24h.toString()))
            // tvPrice.setText("₹"+dataDTO.quote?.uSD?.price.toString())
            val number2=java.lang.Double.valueOf(wishlistEntity.change)
            val credits2=dec.format(number2)
            if (wishlistEntity.change>0){
                tvChange.setTextColor(Color.GREEN)
            }else{
                tvChange.setTextColor(Color.RED)
            }
            tvChange.setText("% "+credits2)
            val number = java.lang.Double.valueOf(wishlistEntity.price)
            val credits = dec.format(number)
            tvPrice.setText("$ "+credits)

        }
    }
}