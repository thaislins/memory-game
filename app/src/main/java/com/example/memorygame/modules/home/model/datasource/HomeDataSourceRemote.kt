package com.example.memorygame.modules.home.model.datasource

import android.util.Log
import com.example.memorygame.modules.home.model.Product
import com.example.memorygame.BuildConfig.ACCESS_TOKEN
import com.example.memorygame.data.ProductService

class HomeDataSourceRemote(private val productService: ProductService?) : HomeDataSource {

    override suspend fun loadAll(): List<Product>? {
        return try {
            val response = productService?.getProducts("1", ACCESS_TOKEN)

            if (response != null) {
                response.products
            } else {
                Log.e("Error", "Error")
                null
            }
        } catch (ex: Exception) {
            Log.e("Error", ex.message)
            null
        }
    }
}