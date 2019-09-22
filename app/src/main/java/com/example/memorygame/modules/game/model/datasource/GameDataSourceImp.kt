package com.example.memorygame.modules.game.model.datasource

import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.home.model.Image
import java.util.*

class GameDataSourceImp : GameDataSource {

    override fun showCards(pairs: Int, amountMatches: Int, images: List<Image>): MutableList<Card> {
        val cards = mutableListOf<Card>()

        for (i in 1..pairs) {
            val card = Card()
            card.image = images[i - 1]
            val card2 = card.copy()
            cards.addAll(listOf(card, card2))
        }
        return cards.shuffled() as MutableList<Card>
    }
}