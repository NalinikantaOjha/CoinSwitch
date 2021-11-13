package com.example.seenu_mobile.popularAdapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.seenu_mobile.AdsAdapter.AdsImage
import com.example.seenu_mobile.R

class popularViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image = itemView.findViewById<ImageView>(R.id.popularImage)
    fun setData1(popularImage1: popularImage1){
        image.setImageResource(popularImage1.populurName())
    }
}