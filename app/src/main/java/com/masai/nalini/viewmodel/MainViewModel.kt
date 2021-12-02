package com.masai.nalini.viewmodel

import androidx.lifecycle.ViewModel
import com.masai.nalini.repository.DataRepository
import androidx.lifecycle.LiveData

import androidx.lifecycle.viewModelScope
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.local.transaction.TransactionEntity
import com.masai.nalini.remote.model.datamodel.ModelDto
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch


class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.getData()
        }
    }

    val user: LiveData<ModelDto>
        get() = dataRepository.user

    fun CreateTransation(transactionEntity: TransactionEntity) {
        dataRepository.CreateTransaction(transactionEntity)

    }

    fun DeleteTransaction(transactionEntity: TransactionEntity) {
        dataRepository.DeleteTransaction(transactionEntity)

    }

    fun UpdateTransaction(transactionEntity: TransactionEntity) {
        dataRepository.UpdateTransaction(transactionEntity)

    }

    fun getAllTransaction(): LiveData<List<TransactionEntity>> {
        return dataRepository.getAllTransaction()
    }

    fun CreateWishList(wishlistEntity: WishlistEntity) {
        dataRepository.createWishList(wishlistEntity)
    }

    fun getWishList(): LiveData<List<WishlistEntity>> {
        return dataRepository.getWishList()
    }

    fun wishListDelete(wishlistEntity: WishlistEntity) {
        return dataRepository.deleteWishList(wishlistEntity)
    }

    fun IsAvalable(user: Int): LiveData<List<WishlistEntity>> {
        return dataRepository.IsAvalable(user)
    }

    fun IsSellble(user: Int): LiveData<List<TransactionEntity>> {
        return dataRepository.IsUpdatable(user)
    }
}