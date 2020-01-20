package com.example.memorygame.modules.score.model.datasource

import com.example.memorygame.modules.score.model.Score

interface ScoreDataSource {
    suspend fun loadAll(): List<Score>?
}