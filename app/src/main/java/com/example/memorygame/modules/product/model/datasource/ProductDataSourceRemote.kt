package com.example.memory_game.modules.product.model.datasource

import android.util.Log
import com.example.memory_game.modules.product.model.Product
import com.example.memorygame.BuildConfig.ACCESS_TOKEN
import com.example.memorygame.data.ProductService

class ProductDataSourceRemote(private val productService: ProductService?) : ProductDataSource {

    override suspend fun loadAll(): List<Product>? {
        try {
            val call = productService?.getProducts("1", ACCESS_TOKEN)
            val response = call?.execute()
            if (response?.isSuccessful!!) {
                Log.d("Response", response.body()?.products?.get(0)?.image?.src.toString())
                return response.body()?.products
            } else {
                Log.e("Error", "Error")
                return null
            }
        } catch (ex: Exception) {
            Log.e("Error", ex.message)
            return null
        }
    }
}