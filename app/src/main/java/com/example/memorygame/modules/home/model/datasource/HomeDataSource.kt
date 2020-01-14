package com.example.memorygame.modules.home.model.datasource

import com.example.memorygame.modules.home.model.Product

interface HomeDataSource {

    suspend fun save(product: Product)
    suspend fun loadAll(): List<Product>?
}