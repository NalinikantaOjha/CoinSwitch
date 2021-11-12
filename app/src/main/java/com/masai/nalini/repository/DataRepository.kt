package com.masai.nalini.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.local.wishListDao
import com.masai.nalini.remote.data.ApiService
import com.masai.nalini.remote.model.datamodel.ModelDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataRepository (val wishListDao: wishListDao,private val userApi:ApiService){
    private val userLiveData=MutableLiveData<ModelDto>()

    val  user:LiveData<ModelDto>
    get()=userLiveData

    suspend fun getData(){

        val result=userApi.getData("de3fcad6-161b-4dfc-9fa1-889c99422601")
        if (result?.body()!=null){
            userLiveData.postValue(result.body())
            Log.d("getdata","response")
        }
    }


    fun createWishList(wishlistEntity: WishlistEntity){
        CoroutineScope(Dispatchers.IO).launch {
            wishListDao.register(wishlistEntity)
        }
    }
    fun getWishList():LiveData<List<WishlistEntity>>{
        return wishListDao.getWishList()
    }
    fun deleteWishList(wishlistEntity: WishlistEntity){
        wishListDao.wishlistDelete(wishlistEntity)

    }

}