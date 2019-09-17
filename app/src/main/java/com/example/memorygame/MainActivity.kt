package com.example.memory_game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.ufrn.sinfo.mesavirtual.data.remote.ProductApi
import com.example.memory_game.modules.product.model.datasource.ProductDataSourceRemote
import com.example.memory_game.modules.product.model.repository.ProductRepository
import com.example.memory_game.modules.product.viewmodel.ProductViewModel
import com.example.memorygame.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Returns instance of the viewmodel
     *
     */
    private fun createViewModel(): ProductViewModel {
        val retrofit = createRetrofit(createLoggingInterceptor())
        val productApi = retrofit.create(ProductApi::class.java)
        val repository = ProductRepository(ProductDataSourceRemote(productApi))

        return ProductViewModel(repository)
    }

    /**
     * Creates Retrofit object using jackson converter factory
     *
     */
    private fun createRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://shopicruit.myshopify.com/").client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
        //page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6
    }

    /**
     * Creates Logging Interceptor Object
     *
     */
    private fun createLoggingInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}
