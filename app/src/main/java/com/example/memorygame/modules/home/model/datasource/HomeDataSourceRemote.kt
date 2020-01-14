package com.example.memorygame.modules.home.model.datasource

import com.example.memorygame.BuildConfig.ACCESS_TOKEN
import com.example.memorygame.data.remote.ProductService
import com.example.memorygame.exceptions.UnableToLoadImagesException
import com.example.memorygame.modules.home.model.Product

class HomeDataSourceRemote(private val productService: ProductService?) : HomeDataSource {

    override suspend fun save(product: Product) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun loadAll(): List<Product>? {
        return try {
            val response = productService?.getProducts("1", ACCESS_TOKEN)

            if (response != null) {
                response.products
            } else {
                throw UnableToLoadImagesException()
            }
        } catch (ex: Exception) {
            throw UnableToLoadImagesException()
        }
    }
}