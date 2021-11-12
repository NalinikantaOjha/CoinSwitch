package com.masai.nalini.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.masai.nalini.repository.DataRepository

class ViewModelFactory (private val dataRepository: DataRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return MainViewModel(dataRepository)as T
    }

}