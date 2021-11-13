package com.example.seenu_mobile.Recommended_Read

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seenu_mobile.R
import com.example.seenu_mobile.popularAdapter.popularViewModel

class RecommendedAdapter(var arrayList3: ArrayList<RecommendedImage>) : RecyclerView.Adapter<RecommendedViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedViewModel {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recommended_item_layout, parent, false)
        return RecommendedViewModel(view)
    }

    override fun onBindViewHolder(holder: RecommendedViewModel, position: Int) {
        var data3 = arrayList3[position]
        holder.setData3(data3)
    }

    override fun getItemCount(): Int {
        return arrayList3.size
    }
}
