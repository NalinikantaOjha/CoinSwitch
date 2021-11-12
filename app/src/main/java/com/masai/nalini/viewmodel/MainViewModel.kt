package com.masai.nalini.viewmodel

import androidx.lifecycle.ViewModel
import com.masai.nalini.repository.DataRepository
import androidx.lifecycle.LiveData

import androidx.lifecycle.viewModelScope
import com.masai.nalini.local.WishlistEntity
import com.masai.nalini.remote.model.datamodel.ModelDto
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch


class MainViewModel(private val dataRepository: DataRepository):ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
            dataRepository.getData()
        }
    }
        val user:LiveData<ModelDto>
        get()=dataRepository.user

fun CreateWishList(wishlistEntity: WishlistEntity){
    dataRepository.createWishList(wishlistEntity)
}
    fun getWishList():LiveData<List<WishlistEntity>>{
        return dataRepository.getWishList()
    }
fun wishListDelete(wishlistEntity: WishlistEntity){
    return dataRepository.deleteWishList(wishlistEntity)
}
}