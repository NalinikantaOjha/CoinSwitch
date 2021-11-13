package com.masai.nalini.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.nalini.R
import com.masai.nalini.remote.model.datamodel.Data
import com.masai.nalini.ui.adapter.listner.OnClickAddToWatchList

class Adapter(
    var list: MutableList<Data>,
    val onClickAddToWatchList: OnClickAddToWatchList,
    var context: Context):RecyclerView.Adapter<DataViewHolder> (){
public fun FILTER(
    list2:MutableList<Data>){
    list=list2
    notifyDataSetChanged()

}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        Log.d("recycle","nalini")

        val inflater = LayoutInflater.from(context)

        val view1: View = inflater.inflate(R.layout.item_layout, parent, false)
        return DataViewHolder(view1,onClickAddToWatchList)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        Log.d("recycle","nalini")
        val type=list.get(position)
        holder.setData(type)
    }

    override fun getItemCount(): Int {
        Log.d("recycle",list.size.toString())
       return list.size
    }
}