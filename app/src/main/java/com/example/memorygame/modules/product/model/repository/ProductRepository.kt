package com.example.memory_game.modules.product.model.repository

import android.util.Log
import com.example.memory_game.modules.product.model.Product
import com.example.memory_game.modules.product.model.datasource.ProductDataSource
import com.example.memory_game.modules.product.model.datasource.ProductDataSourceRemote

class ProductRepository(private val remoteDataSource: ProductDataSourceRemote) : ProductDataSource {

    override suspend fun loadAll(): List<Product>? {
        val products = remoteDataSource.loadAll()
        Log.e("RESULTTT", products?.get(0)?.title.toString())
        return products
    }
}