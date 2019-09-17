package br.ufrn.sinfo.mesavirtual.data.remote

import com.example.memory_game.modules.product.model.Product
import com.example.memory_game.modules.product.model.Shopify
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApi {

    @GET("admin/products.json?")
    fun getProducts(
            @Query("page") page: String, @Query("access_token") accessToken: String
    ): Call<Shopify>?
}