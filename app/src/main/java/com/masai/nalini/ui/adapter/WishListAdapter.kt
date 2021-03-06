package com.masai.nalini.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.nalini.R
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.remote.model.datamodel.Data
import com.masai.nalini.ui.adapter.listner.OnClickDeleteWishList

class WishListAdapter(var list: MutableList<WishlistEntity>,val onClickDeleteWishList: OnClickDeleteWishList,
                      var context: Context):RecyclerView.Adapter<wishListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): wishListViewHolder {
        val inflater = LayoutInflater.from(context)

        val view1: View = inflater.inflate(R.layout.watchlist_item_layout, parent, false)
        return wishListViewHolder(view1,onClickDeleteWishList)
    }

    override fun onBindViewHolder(holder: wishListViewHolder, position: Int) {
        val type=list.get(position)
        holder.setData(type)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}