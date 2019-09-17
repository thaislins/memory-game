package com.example.memory_game.modules.product.model.repository

import com.example.memory_game.modules.product.model.Product
import com.example.memory_game.modules.product.model.datasource.ProductDataSource
import com.example.memory_game.modules.product.model.datasource.ProductDataSourceRemote

class ProductRepository(private val remoteDataSource: ProductDataSourceRemote) : ProductDataSource {

    override suspend fun loadAll(): List<Product>? {
        return remoteDataSource.loadAll()
    }
}