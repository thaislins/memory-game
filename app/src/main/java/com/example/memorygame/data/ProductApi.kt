package com.example.memorygame.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class ProductApi {

    /**
     * Creates a retrofit object to be used for a request
     *
     */
    fun getProductService(): ProductService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://shopicruit.myshopify.com/")
            .addConverterFactory(JacksonConverterFactory.create())
            .client(createLoggingInterceptor())
            .build()

        return retrofit.create(ProductService::class.java)
    }

    /**
     * Creates logging object that logs all details about related request
     *
     */
    private fun createLoggingInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}