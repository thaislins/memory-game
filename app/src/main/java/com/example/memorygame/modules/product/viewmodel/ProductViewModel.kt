package com.example.memory_game.modules.product.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memory_game.modules.product.model.Product
import com.example.memory_game.modules.product.model.datasource.ProductDataSourceRemote
import com.example.memory_game.modules.product.model.repository.ProductRepository
import com.example.memorygame.data.ProductApi
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductViewModel() : ViewModel(), KoinComponent {

    val productApi: ProductApi? by inject()

    private val repository by lazy {
        ProductRepository(ProductDataSourceRemote(productApi?.getProductService()))
    }

    val products = MutableLiveData<List<Product>>().apply { value = emptyList() }

    /**
     * This method launches a coroutine to allow an
     * API request
     *
     */
    suspend fun loadProducts() {
        try {
            products.postValue(repository.loadAll())
        } catch (ex: Exception) {
            Log.e("Error", ex.stackTrace.toString())
        }
    }
}