package com.masai.nalini.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.masai.nalini.repository.DataRepository
import androidx.lifecycle.LiveData

import androidx.lifecycle.viewModelScope
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.local.transaction.TransactionEntity
import com.masai.nalini.remote.model.datamodel.ModelDto
import kotlinx.coroutines.*



class MainViewModel(private var dataRepository: DataRepository):ViewModel() {



    @InternalCoroutinesApi
   fun dataLive():LiveData<ModelDto>{
       viewModelScope.launch (Dispatchers.IO){
           while (NonCancellable.isActive){
               dataRepository.getData()
               delay(50000)
               Log.d("nalini","call")
           }

       }
       return dataRepository.user
   }



    fun CreateTransation(transactionEntity: TransactionEntity){

        dataRepository.CreateTransaction(transactionEntity)

    }
    fun DeleteTransaction(transactionEntity: TransactionEntity){
        dataRepository.DeleteTransaction(transactionEntity)

    }
    fun UpdateTransaction(transactionEntity: TransactionEntity){
        dataRepository.UpdateTransaction(transactionEntity)

    }
    fun getAllTransaction():LiveData<List<TransactionEntity>>{
        return dataRepository.getAllTransaction()
    }

fun CreateWishList(wishlistEntity: WishlistEntity){
    dataRepository.createWishList(wishlistEntity)
}
    fun getWishList():LiveData<List<WishlistEntity>>{
        return dataRepository.getWishList()
    }
fun wishListDelete(wishlistEntity: WishlistEntity){
    return dataRepository.deleteWishList(wishlistEntity)
}
fun IsAvalable(user:Int): LiveData<List<WishlistEntity>> {
    return dataRepository.IsAvalable(user)
}
    fun IsSellble(user:Int):LiveData<List<TransactionEntity>>{
        return dataRepository.IsUpdatable(user)
    }
}