package com.example.memorygame.modules.game.model.datasource

import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.home.model.Image
import com.example.memorygame.modules.score.model.Score

interface GameDataSource {
    fun showCards(amountOfSets: Int, amountEqualCards: Int, images: List<Image>): MutableList<Card>
    suspend fun saveScore(score: Score)
}