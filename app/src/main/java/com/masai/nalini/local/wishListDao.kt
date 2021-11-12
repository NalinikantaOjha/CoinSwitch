package com.masai.nalini.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface wishListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun register(wishlistEntity: WishlistEntity)

    @Query("select * from wishlistTable")
    fun getWishList():LiveData<List<WishlistEntity>>
    @Delete
    fun wishlistDelete(wishlistEntity: WishlistEntity)

}