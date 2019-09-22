package com.example.memorygame.modules.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorygame.data.ProductApi
import com.example.memorygame.modules.home.model.Product
import com.example.memorygame.modules.home.model.datasource.HomeDataSourceRemote
import com.example.memorygame.modules.home.model.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val productApi: ProductApi? by inject()

    private val repository by lazy {
        HomeRepository(HomeDataSourceRemote(productApi?.getProductService()))
    }

    val products = MutableLiveData<ArrayList<Product>>()

    /**
     * This method launches a coroutine to make an
     * API request
     *
     */
    fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                products.postValue(repository.loadAll() as ArrayList<Product>?)
            } catch (ex: Exception) {
                Log.e("Error", ex.stackTrace.toString())
            }
        }
    }
}