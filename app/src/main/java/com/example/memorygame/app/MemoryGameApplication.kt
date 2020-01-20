package com.example.memorygame.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.memorygame.core.AppDatabase
import com.example.memorygame.data.local.ProductDao
import com.example.memorygame.data.local.ScoreDao
import com.example.memorygame.data.remote.ProductApi
import com.example.memorygame.modules.game.model.datasource.GameDataSourceImp
import com.example.memorygame.modules.game.model.repository.GameRepository
import com.example.memorygame.modules.home.model.datasource.HomeDataSourceLocal
import com.example.memorygame.modules.home.model.datasource.HomeDataSourceRemote
import com.example.memorygame.modules.home.model.repository.HomeRepository
import com.example.memorygame.modules.home.viewmodel.HomeViewModel
import com.example.memorygame.modules.score.model.datasource.ScoreDataSourceImp
import com.example.memorygame.modules.score.model.repository.ScoreRepository
import com.example.memorygame.modules.score.viewmodel.ScoreViewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MemoryGameApplication : Application() {

    private val productApi: ProductApi? by inject()
    private val productDao: ProductDao by inject()
    private val scoreDao: ScoreDao by inject()

    private val listofModules = module {
        single { ProductApi() }

        // ViewModel
        viewModel { HomeViewModel() }
        viewModel { ScoreViewModel() }

        // Repository
        single { GameRepository(GameDataSourceImp(scoreDao)) }
        single { ScoreRepository(ScoreDataSourceImp(scoreDao)) }
        single {
            HomeRepository(
                HomeDataSourceLocal(productDao),
                HomeDataSourceRemote(productApi?.getProductService())
            )
        }

        // Database
        single { Room.databaseBuilder(get(), AppDatabase::class.java, "db").build() }
        single { get<AppDatabase>().productDao() }
        single { get<AppDatabase>().scoreDao() }
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
            modules(listofModules) // declare modules
        }
    }
}