package com.masai.nalini.ui.activity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


import com.masai.nalini.R

class CoinSwitchViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image = itemView.findViewById<ImageView>(R.id.popularImage)
    var text = itemView.findViewById<TextView>(R.id.tvCoinSwitchTV1)
    fun setData1(coinSwitchImage: CoinSwitchImage){
        image.setImageResource(coinSwitchImage.populurName())
        text.setText(coinSwitchImage.coinSwitchads())
    }
}