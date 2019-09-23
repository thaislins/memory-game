package com.example.memorygame.adapter

import com.example.memorygame.modules.game.model.Card

interface AdapterItemsContract {
    fun replaceItems(list: MutableList<Card>)
}