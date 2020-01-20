package com.example.memorygame.modules.game.view.adapter

import com.example.memorygame.modules.game.model.Card

interface AdapterCardsContract {
    fun setCardList(list: MutableList<Card>)
}