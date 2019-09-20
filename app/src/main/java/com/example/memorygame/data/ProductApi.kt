package com.example.memorygame.data

import com.example.memorygame.BuildConfig.BASE_URL
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
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(createLoggingInterceptor())
            .build()

        return retrofit.create(ProductService::class.java)
    }

    /**
     * Creates logging object that logs details about related request
     *
     */
    private fun createLoggingInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}