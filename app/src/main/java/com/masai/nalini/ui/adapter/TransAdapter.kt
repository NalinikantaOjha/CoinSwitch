package com.masai.nalini.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.nalini.R
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.local.transaction.TransactionEntity
import com.masai.nalini.ui.adapter.listner.OnClickDeleteWishList
import com.masai.nalini.ui.adapter.listner.OnTransctionListner

class TransAdapter(
    var list: MutableList<TransactionEntity>, val onTransctionListner: OnTransctionListner,
    var context: Context):RecyclerView.Adapter<TransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(context)

        val view1: View = inflater.inflate(R.layout.item_layout, parent, false)
        return TransactionViewHolder(view1,onTransctionListner)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val type=list.get(position)
        holder.setDataTrans(type)
    }

    override fun getItemCount(): Int {
       return list.size
    }
}