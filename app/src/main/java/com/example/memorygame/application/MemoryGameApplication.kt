package com.example.memorygame.application

import android.app.Application
import android.content.Context
import com.example.memory_game.modules.product.viewmodel.ProductViewModel
import com.example.memorygame.data.ProductApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MemoryGameApplication : Application() {

    val listofModules = module {
        single { ProductApi() } // single instance of ProductApi
        single { ProductViewModel() } // single instance of ProductViewModel
    }

    companion object {
        var appContext: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        startKoin {
            androidContext(this@MemoryGameApplication)
            androidLogger()
            // declare modules
            modules(listofModules)
        }
    }
}