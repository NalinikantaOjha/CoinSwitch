package com.masai.nalini.ui.activity

import android.media.Image
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.masai.nalini.R


class AdsViewModel(itemView: View) :RecyclerView.ViewHolder(itemView) {
     var image = itemView.findViewById<ImageView>(R.id.adsImage)
     fun setData(adsImage: AdsImage){
      image.setImageResource(adsImage.getName())
    }
    }


