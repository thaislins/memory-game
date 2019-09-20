package com.example.memorygame.modules.game.model.datasource

import com.example.memory_game.modules.card.model.Card

interface GameDataSource {
    fun showCards(pairs: Int, amountMatches: Int) : MutableList<Card>
}