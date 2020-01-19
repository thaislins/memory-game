package com.example.memorygame.modules.game.model.datasource

import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.home.model.Image

class GameDataSourceImp : GameDataSource {

    override fun showCards(
        amountOfSets: Int,
        amountEqualCards: Int,
        images: List<Image>
    ): MutableList<Card> {
        val cards = mutableListOf<Card>()

        for (i in 1..amountOfSets) {
            val card = Card()
            card.image = images[i-1]
            cards.add(card)

            for (j in 1 until amountEqualCards) {
                val copiedCard = card.copy()
                cards.add(copiedCard)
            }
        }

        return cards.shuffled() as MutableList<Card>
    }
}