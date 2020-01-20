package com.example.memorygame.core

import android.widget.GridView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.modules.game.view.adapter.AdapterCardsContract
import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.score.model.Score
import com.example.memorygame.modules.score.view.adapter.AdapterScoresContract

object BindingAdapters {
    @BindingAdapter("cards")
    @JvmStatic
    fun setCards(gridView: GridView, cards: MutableList<Card>) {
        gridView.adapter.let {
            if (it is AdapterCardsContract) {
                it.setCardList(cards)
            }
        }
    }

    @BindingAdapter("items")
    @JvmStatic
    fun setScores(recyclerView: RecyclerView, items: MutableList<Score>?) {
        recyclerView.adapter.let {
            if (it is AdapterScoresContract) {
                it.setScoreList(items)
            }
        }
    }
}

