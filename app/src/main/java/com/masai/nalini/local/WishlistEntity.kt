package com.masai.nalini.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlistTable")
data class WishlistEntity(
    @ColumnInfo(name = "name")var name:String,
    @PrimaryKey(autoGenerate = false)@ColumnInfo(name="id")var id:Int,
    @ColumnInfo(name="symbol")var symbol:String,
    @ColumnInfo(name="price") var price:Double,
    @ColumnInfo(name="change")var change:Double)
{
}