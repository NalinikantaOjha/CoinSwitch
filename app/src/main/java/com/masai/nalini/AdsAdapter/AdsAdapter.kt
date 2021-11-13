package com.example.seenu_mobile.AdsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seenu_mobile.R

class AdsAdapter(private var array: ArrayList<AdsImage>): RecyclerView.Adapter<AdsViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewModel {
      var view = LayoutInflater.from(parent.context).inflate(R.layout.ads_item_layout, parent, false)
      return AdsViewModel(view)
    }

    override fun onBindViewHolder(holder: AdsViewModel, position: Int) {
        var data = array[position]
        holder.setData(data)
    }

    override fun getItemCount(): Int {
       return array.size
    }
}