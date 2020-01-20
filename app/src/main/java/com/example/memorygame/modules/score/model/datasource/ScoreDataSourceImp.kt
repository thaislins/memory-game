package com.example.memorygame.modules.score.model.datasource

import com.example.memorygame.data.local.ScoreDao
import com.example.memorygame.modules.score.model.Score

class ScoreDataSourceImp(private val scoreDao: ScoreDao) : ScoreDataSource {

    override suspend fun loadAll(): List<Score>? {
        return scoreDao.getAll()
    }
}