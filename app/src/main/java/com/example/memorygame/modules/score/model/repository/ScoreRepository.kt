package com.example.memorygame.modules.score.model.repository

import com.example.memorygame.modules.score.model.Score
import com.example.memorygame.modules.score.model.datasource.ScoreDataSource

class ScoreRepository(private val scoreDataSource: ScoreDataSource) : ScoreDataSource {

    override suspend fun loadAll(): List<Score>? {
        return scoreDataSource.loadAll()
    }
}