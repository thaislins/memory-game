package com.example.memorygame.modules.game.model.repository

import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.game.model.datasource.GameDataSource
import com.example.memorygame.modules.game.model.datasource.GameDataSourceImp
import com.example.memorygame.modules.home.model.Image

class GameRepository(private val remoteDataSource: GameDataSourceImp) : GameDataSource {

    override fun showCards(amountOfPairs: Int, images: List<Image>): MutableList<Card> {
        return remoteDataSource.showCards(amountOfPairs, images)
    }
}