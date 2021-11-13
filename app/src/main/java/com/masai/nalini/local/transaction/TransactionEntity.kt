package com.masai.nalini.local.transaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactionTable")
data class TransactionEntity(
    @ColumnInfo(name="amount")var amount:Int,
    @ColumnInfo(name = "name")var name:String,
    @PrimaryKey(autoGenerate = false)@ColumnInfo(name="id")var id:Int,
    @ColumnInfo(name="symbol")var symbol:String,
    @ColumnInfo(name="price") var price:Double,
    @ColumnInfo(name="change")var change:Double)
{
}