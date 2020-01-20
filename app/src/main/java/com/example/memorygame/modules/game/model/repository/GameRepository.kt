package com.example.memorygame.modules.game.model.repository

import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.game.model.datasource.GameDataSource
import com.example.memorygame.modules.game.model.datasource.GameDataSourceImp
import com.example.memorygame.modules.home.model.Image
import com.example.memorygame.modules.score.model.Score

class GameRepository(private val remoteDataSource: GameDataSourceImp) : GameDataSource {

    override fun showCards(
        amountOfSets: Int,
        amountEqualCards: Int,
        images: List<Image>
    ): MutableList<Card> {
        return remoteDataSource.showCards(amountOfSets, amountEqualCards, images)
    }

    override suspend fun saveScore(score: Score) {
        remoteDataSource.saveScore(score)
    }
}