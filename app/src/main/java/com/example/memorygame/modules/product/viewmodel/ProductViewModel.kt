package com.example.memory_game.modules.product.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memory_game.modules.product.model.Product
import com.example.memory_game.modules.product.model.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val products = MutableLiveData<List<Product>>().apply { value = emptyList() }

    fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                products.postValue(repository.loadAll())
                Log.e("Success", "Success")
            } catch (ex: Exception) {
                Log.e("Error", ex.message)
            }
        }
    }
}