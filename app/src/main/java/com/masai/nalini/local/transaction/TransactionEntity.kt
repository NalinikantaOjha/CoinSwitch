package com.masai.nalini.local.transaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactionTable")
data class TransactionEntity(

    @ColumnInfo(name = "name")var name:String="",
    @PrimaryKey(autoGenerate = false)@ColumnInfo(name="id")var id:Int=0,
    @ColumnInfo(name="symbol")var symbol:String="",
    @ColumnInfo(name="change")var change:Double=0.00,
    @ColumnInfo(name = "changeprofit")var changeprofit:Double=0.0,

    @ColumnInfo(name="per")var per:Double=0.0,
@ColumnInfo(name="amount")var amount:Int=0,
@ColumnInfo(name="price") var price:Double=0.00)


{
}