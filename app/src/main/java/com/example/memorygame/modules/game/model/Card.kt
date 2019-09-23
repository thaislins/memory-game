package com.example.memorygame.modules.game.model

import com.example.memorygame.modules.home.model.Image

data class Card(
    var id: Int,
    var isFaceUp: Boolean,
    var isMatched: Boolean,
    var image: Image?
) {
    constructor() : this(getUniqueId(), true, false, null)

    companion object {
        var identifierFactory: Int = 0

        private fun getUniqueId(): Int {
            identifierFactory += 1
            return identifierFactory
        }
    }
}