package com.example.memorygame.modules.home.model.repository

import com.example.memorygame.modules.home.model.Image
import com.example.memorygame.modules.home.model.Product
import com.example.memorygame.modules.home.model.datasource.HomeDataSource
import com.example.memorygame.modules.home.model.datasource.HomeDataSourceLocal
import com.example.memorygame.modules.home.model.datasource.HomeDataSourceRemote

class HomeRepository(
    private val localDataSource: HomeDataSourceLocal,
    private val remoteDataSource: HomeDataSourceRemote
) : HomeDataSource {

    override suspend fun save(product: Product) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun loadAll(): List<Product>? {
        val dbRequest = localDataSource.loadAll()

        return if (!dbRequest.isNullOrEmpty()) {
            dbRequest.forEach {
                it.image = Image()
                it.image!!.src = it.imageSrc
            }
            dbRequest
        } else {
            val list = remoteDataSource.loadAll()
            list?.forEach { localDataSource.save(it) }
            list
        }
    }
}