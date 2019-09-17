package com.example.memory_game.modules.product.model.datasource

import com.example.memory_game.modules.product.model.Product

interface ProductDataSource {

    suspend fun loadAll(): List<Product>?;
}