package com.masai.nalini.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WishlistEntity::class],version = 1)

abstract class WishListData:RoomDatabase() {
    abstract fun getWishList():wishListDao
    companion object{
        private var instance:WishListData?=null
        fun getWishListDatabase(context: Context):WishListData{
            if (instance!=null){
                return instance!!
            }else{
                val builder= Room.databaseBuilder(
                    context.applicationContext,
                    WishListData::class.java,
                    "wishlistdb"
                )
                builder.fallbackToDestructiveMigration()
                instance=builder.build()
                return instance!!
            }

        }
    }
}