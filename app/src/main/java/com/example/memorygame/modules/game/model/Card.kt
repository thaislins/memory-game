package com.example.memory_game.modules.card.model

import com.example.memory_game.modules.product.model.Image

class Card {
    var id: Int
    var isFaceUp: Boolean = false
    var isMatched: Boolean = false
    var image: Image? = null

    companion object {
        var identifierFactory: Int = 0

        private fun getUniqueId(): Int {
            identifierFactory += 1
            return identifierFactory
        }
    }

    init {
        id = getUniqueId()
    }

}