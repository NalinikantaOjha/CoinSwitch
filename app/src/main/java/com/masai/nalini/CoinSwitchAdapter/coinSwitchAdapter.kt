package com.example.seenu_mobile.CoinSwitchAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seenu_mobile.R
import com.example.seenu_mobile.popularAdapter.popularViewModel

class coinSwitchAdapter(var arrayList2: ArrayList<CoinSwitchImage>) : RecyclerView.Adapter<CoinSwitchViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinSwitchViewModel {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.coinswitchitem_layout, parent, false)
        return CoinSwitchViewModel(view)
    }

    override fun onBindViewHolder(holder: CoinSwitchViewModel, position: Int) {
        var data1 = arrayList2[position]
        holder.setData1(data1)
    }

    override fun getItemCount(): Int {
        return arrayList2.size

    }

}
