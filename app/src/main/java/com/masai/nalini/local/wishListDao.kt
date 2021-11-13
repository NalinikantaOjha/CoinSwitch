package com.masai.nalini.local

import androidx.lifecycle.LiveData
import androidx.room.*
import retrofit2.http.GET

@Dao
interface wishListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun register(wishlistEntity: WishlistEntity)
    @Query("select * from wishlistTable")
    fun getWishList():LiveData<List<WishlistEntity>>
    @Delete
    fun wishlistDelete(wishlistEntity: WishlistEntity)

    @Query("SELECT * FROM wishlistTable WHERE id IN(:userIds)")
    fun findByIds(userIds: Int): LiveData<List<WishlistEntity>>
}