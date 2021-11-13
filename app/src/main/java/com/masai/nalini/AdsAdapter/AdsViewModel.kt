package com.example.seenu_mobile.AdsAdapter

import android.media.Image
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.seenu_mobile.R

class AdsViewModel(itemView: View) :RecyclerView.ViewHolder(itemView) {
     var image = itemView.findViewById<ImageView>(R.id.adsImage)
     fun setData(adsImage: AdsImage){
      image.setImageResource(adsImage.getName())
    }
    }


