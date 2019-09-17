package com.example.memory_game.modules.product.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memory_game.modules.product.model.Product
import com.example.memory_game.modules.product.model.datasource.ProductDataSourceRemote
import com.example.memory_game.modules.product.model.repository.ProductRepository
import com.example.memorygame.data.ProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProductViewModel() : ViewModel(), KoinComponent {

    val productApi: ProductApi? by inject()

    private val repository by lazy {
        ProductRepository(ProductDataSourceRemote(productApi?.getProductService()))
    }

    val products = MutableLiveData<List<Product>>().apply { value = emptyList() }

    fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                products.postValue(repository.loadAll())
            } catch (ex: Exception) {
                Log.e("Error", ex.message)
            }
        }
    }
}