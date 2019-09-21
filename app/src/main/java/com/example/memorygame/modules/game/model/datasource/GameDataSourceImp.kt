package com.example.memorygame.modules.game.model.datasource

import com.example.memory_game.modules.card.model.Card
import com.example.memory_game.modules.product.model.Image

class GameDataSourceImp : GameDataSource {

    override fun showCards(pairs: Int, amountMatches: Int, images: List<Image>): MutableList<Card> {
        var cards = mutableListOf<Card>()

        for (i in 1..pairs) {
            val card = Card()
            card.image = images[i - 1]
            cards.addAll(MutableList(amountMatches) { card })
        }
        return cards.shuffled() as MutableList<Card>
    }
}