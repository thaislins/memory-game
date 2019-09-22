package com.example.memorygame.app

import android.app.Application
import android.content.Context
import com.example.memorygame.data.ProductApi
import com.example.memorygame.modules.home.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MemoryGameApplication : Application() {

    private val listofModules = module {
        single { ProductApi() }
        factory { HomeViewModel() }
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