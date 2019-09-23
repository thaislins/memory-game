package com.example.memorygame.app

import android.app.Application
import android.content.Context
import com.example.memorygame.data.ProductApi
import com.example.memorygame.modules.game.model.datasource.GameDataSourceImp
import com.example.memorygame.modules.game.model.repository.GameRepository
import com.example.memorygame.modules.home.model.datasource.HomeDataSourceRemote
import com.example.memorygame.modules.home.model.repository.HomeRepository
import com.example.memorygame.modules.home.viewmodel.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MemoryGameApplication : Application() {

    private val productApi: ProductApi? by inject()

    private val listofModules = module {
        single { ProductApi() }
        single { HomeRepository(HomeDataSourceRemote(productApi?.getProductService())) }
        single { GameRepository(GameDataSourceImp()) }
        viewModel { HomeViewModel() }
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