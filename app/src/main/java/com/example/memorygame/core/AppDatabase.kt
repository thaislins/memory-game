package com.example.memorygame.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.memorygame.data.local.ProductDao
import com.example.memorygame.data.local.ScoreDao
import com.example.memorygame.modules.home.model.Product
import com.example.memorygame.modules.score.model.Score

@Database(entities = [Product::class, Score::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun scoreDao(): ScoreDao
}