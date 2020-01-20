package com.example.memorygame.modules.score.view.adapter

import com.example.memorygame.modules.score.model.Score

interface AdapterScoresContract {
    fun setScoreList(list: List<Score>?)
}