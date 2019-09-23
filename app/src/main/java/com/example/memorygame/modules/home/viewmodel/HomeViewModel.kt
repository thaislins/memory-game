package com.example.memorygame.modules.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorygame.modules.home.model.Product
import com.example.memorygame.modules.home.model.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val repository: HomeRepository by inject()
    val error = MutableLiveData<Boolean>().apply { value = false }
    val products = MutableLiveData<ArrayList<Product>>()

    /**
     * This method launches a coroutine to make an
     * API request
     *
     */
    fun loadProducts() {
        error.value = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                products.postValue(repository.loadAll() as ArrayList<Product>?)
            } catch (ex: Exception) {
                error.postValue(true)
            }
        }
    }
}