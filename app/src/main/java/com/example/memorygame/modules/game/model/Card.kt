package com.example.memory_game.modules.card.model

class Card {
    var id: Int
    var isFaceUp: Boolean = false
    var isMatched: Boolean = false

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