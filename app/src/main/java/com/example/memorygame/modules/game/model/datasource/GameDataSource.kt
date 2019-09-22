package com.example.memorygame.modules.game.model.datasource

import com.example.memory_game.modules.card.model.Card
import com.example.memory_game.modules.product.model.Image

interface GameDataSource {
    fun showCards(pairs: Int, amountMatches: Int, images: List<Image>): MutableList<Card>
}