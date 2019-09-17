package com.example.memory_game.modules.product.model.datasource

import android.util.Log
import br.ufrn.sinfo.mesavirtual.data.remote.ProductApi
import com.example.memory_game.modules.product.model.Product
import com.example.memory_game.modules.product.model.Shopify
import retrofit2.Response
import java.io.IOException

class ProductDataSourceRemote(private val productApi: ProductApi) : ProductDataSource {

    override suspend fun loadAll(): List<Product>? {
        var response: Response<Shopify>? = null

        try {
            val call = productApi.getProducts("1", "c32313df0d0ef512ca64d5b336a0d7c6")
            response = call?.execute()
            if (response?.isSuccessful!!) {
                Log.d("Response", response.body()?.products?.get(0)?.image?.src.toString())
                return response.body()?.products
            } else {
                Log.e("Error", "Error")
                return null
            }
        } catch (ex: Exception) {
            Log.e("Error", ex.message)
            return null
        }
    }
}