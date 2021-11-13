package com.masai.nalini.local.transaction

import androidx.lifecycle.LiveData
import androidx.room.*
import com.masai.nalini.local.WishlistEntity

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun register(transactionDao: TransactionEntity)
    @Query("select * from transactionTable")
    fun getTransaction():LiveData<List<TransactionEntity>>
    @Update
    fun updateTransaction(transactionEntity: TransactionEntity)
    @Delete
    fun deleteTransaction(transactionEntity: TransactionEntity)
    @Query("SELECT * FROM transactionTable WHERE id IN(:userIds)")
    fun findByIdSell(userIds: Int): LiveData<List<TransactionEntity>>
   /* @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun register(transactionDao: TransactionDao)
    @Query("select * from `transaction`")
    fun getWishList(): LiveData<List<WishlistEntity>>
    @Delete
    fun wishlistDelete(wishlistEntity: WishlistEntity)

    @Query("SELECT * FROM `transaction` WHERE id IN(:userIds)")
    fun findByIds(userIds: Int): LiveData<List<WishlistEntity>>*/
}