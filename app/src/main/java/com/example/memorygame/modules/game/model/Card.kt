package com.example.memory_game.modules.card.model

import com.example.memory_game.modules.product.model.Image

data class Card(
    var id: Int,
    var isFaceUp: Boolean,
    var isMatched: Boolean,
    var image: Image?
) {
    constructor() : this(getUniqueId(), false, false, null)

    companion object {
        var identifierFactory: Int = 0

        private fun getUniqueId(): Int {
            identifierFactory += 1
            return identifierFactory
        }
    }
}