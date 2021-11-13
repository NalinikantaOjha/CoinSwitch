package com.masai.nalini.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.local.transaction.TransactionDao
import com.masai.nalini.local.transaction.TransactionEntity
import com.masai.nalini.local.wishListDao
import com.masai.nalini.remote.data.ApiService
import com.masai.nalini.remote.model.datamodel.ModelDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataRepository (val transactionDao: TransactionDao,val wishListDao: wishListDao,private val userApi:ApiService){
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
fun CreateTransaction(transactionEntity: TransactionEntity){
    CoroutineScope(Dispatchers.IO).launch {
        transactionDao.register(transactionEntity)
    }
}
    fun DeleteTransaction(transactionEntity: TransactionEntity){
        CoroutineScope(Dispatchers.IO).launch {
            transactionDao.deleteTransaction(transactionEntity)
        }
    }
    fun getAllTransaction():LiveData<List<TransactionEntity>>{
       return transactionDao.getTransaction()

    }
    fun UpdateTransaction(transactionEntity: TransactionEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            transactionDao.deleteTransaction(transactionEntity)
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
        CoroutineScope(Dispatchers.IO).launch {
            wishListDao.wishlistDelete(wishlistEntity)
        }


    }
fun IsAvalable(user:Int): LiveData<List<WishlistEntity>> {
    return wishListDao.findByIds(user)
}
    fun IsUpdatable(user:Int):LiveData<List<TransactionEntity>>{
        return transactionDao.findByIdSell(user)
    }

}