package com.example.memorygame.modules.home.model.repository

import com.example.memorygame.modules.home.model.Product
import com.example.memorygame.modules.home.model.datasource.HomeDataSource
import com.example.memorygame.modules.home.model.datasource.HomeDataSourceRemote

class HomeRepository(private val remoteDataSource: HomeDataSourceRemote) : HomeDataSource {

    override suspend fun loadAll(): List<Product>? {
        return remoteDataSource.loadAll()
    }
}