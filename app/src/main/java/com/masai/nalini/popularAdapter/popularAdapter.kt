package com.masai.nalini.ui.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.nalini.R


class popularAdapter(var arrayList1: ArrayList<popularImage1>) : RecyclerView.Adapter<popularViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): popularViewModel {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.popular_item_layout, parent, false)
        return popularViewModel(view)
    }

    override fun onBindViewHolder(holder: popularViewModel, position: Int) {
        var data1 = arrayList1[position]
        holder.setData1(data1)
    }

    override fun getItemCount(): Int {
        return arrayList1.size
    }
}
