package com.example.memorygame.modules.game.model.repository

import com.example.memory_game.modules.card.model.Card
import com.example.memory_game.modules.product.model.Image
import com.example.memorygame.modules.game.model.datasource.GameDataSource
import com.example.memorygame.modules.game.model.datasource.GameDataSourceImp

class GameRepository(private val remoteDataSource: GameDataSourceImp) : GameDataSource {

    override fun showCards(pairs: Int, amountMatches: Int, images: List<Image>): MutableList<Card> {
        return remoteDataSource.showCards(pairs, amountMatches, images)
    }
}