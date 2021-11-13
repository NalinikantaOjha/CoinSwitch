package com.masai.nalini.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.masai.nalini.R
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.ui.adapter.listner.OnClickDeleteWishList
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.watchlist_item_layout.view.*
import kotlinx.android.synthetic.main.watchlist_item_layout.view.imageurl
import kotlinx.android.synthetic.main.watchlist_item_layout.view.tvChange
import kotlinx.android.synthetic.main.watchlist_item_layout.view.tvLogo
import kotlinx.android.synthetic.main.watchlist_item_layout.view.tvName
import kotlinx.android.synthetic.main.watchlist_item_layout.view.tvPrice
import java.text.DecimalFormat

class wishListViewHolder(itemView:View,val onClickDeleteWishList: OnClickDeleteWishList): RecyclerView.ViewHolder(itemView) {
    val dec = DecimalFormat("#.##")
    fun setData(wishlistEntity: WishlistEntity){
        itemView.apply {
            Log.d("recycle","nalini")
            tvName.setText(wishlistEntity.name.toString())
            tvLogo.setText(wishlistEntity.symbol.toString())
            if(wishlistEntity.name.equals("Bitcoin")){
                imageurl.setImageResource(R.drawable.bitcoin)
            }else if (wishlistEntity.name.equals("Ethereum")){
                imageurl.setImageResource(R.drawable.therum)
            }else if (wishlistEntity.name.equals("Tether")){
                imageurl.setImageResource(R.drawable.thther)
            }else if (wishlistEntity.name.equals("Solana")){
                imageurl.setImageResource(R.drawable.solana)
            }
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
ivDelete.setOnClickListener {
    onClickDeleteWishList.OnDelteWatch(wishlistEntity)
}
        }

    }
}