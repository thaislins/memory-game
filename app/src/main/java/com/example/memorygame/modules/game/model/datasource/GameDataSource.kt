package com.example.memorygame.modules.game.model.datasource

import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.home.model.Image

interface GameDataSource {
    fun showCards(amountOfPairs: Int, images: List<Image>): MutableList<Card>
}