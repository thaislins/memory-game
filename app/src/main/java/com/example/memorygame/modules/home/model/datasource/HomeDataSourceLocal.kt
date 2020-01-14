package com.example.memorygame.modules.home.model.datasource

import com.example.memorygame.data.local.ProductDao
import com.example.memorygame.modules.home.model.Product

class HomeDataSourceLocal(private val dao: ProductDao) : HomeDataSource {

    override suspend fun save(product: Product) {
        dao.insertProductWithImage(product)
    }

    override suspend fun loadAll(): List<Product>? {
        return dao.getAll()
    }
}