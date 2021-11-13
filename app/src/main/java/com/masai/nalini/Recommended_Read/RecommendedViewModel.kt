package com.masai.nalini.ui.activity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.masai.nalini.R


class RecommendedViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image = itemView.findViewById<ImageView>(R.id.recommended_Image)
    var text = itemView.findViewById<TextView>(R.id.recommended_name)
    fun setData3(recommendedImage: RecommendedImage){
        image.setImageResource(recommendedImage.RecommendedImage())
        text.setText(recommendedImage.RecommendedName())
    }
}