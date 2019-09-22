package com.example.memorygame.data

import com.example.memorygame.modules.home.model.Shopify
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("admin/products.json?")
    suspend fun getProducts(
        @Query("page") page: String, @Query("access_token") accessToken: String
    ): Shopify
}