package com.example.memorygame.modules.game.model.datasource

import com.example.memory_game.modules.card.model.Card

class GameDataSourceImp : GameDataSource {

    override fun showCards(pairs: Int, amountMatches: Int): MutableList<Card> {
        var cards = mutableListOf<Card>()

        for (i in 1..pairs) {
            val card = Card()
            cards.addAll(MutableList(amountMatches) { card })
        }
        return cards.shuffled() as MutableList<Card>
    }
}