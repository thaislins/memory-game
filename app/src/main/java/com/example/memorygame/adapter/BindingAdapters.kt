package com.example.memorygame.adapter

import android.widget.GridView
import androidx.databinding.BindingAdapter
import com.example.memorygame.modules.game.model.Card

object BindingAdapters {
    @BindingAdapter("cards")
    @JvmStatic
    fun setCards(gridView: GridView, items: MutableList<Card>) {

        gridView.adapter.let {
            if (it is AdapterItemsContract) {
                it.replaceItems(items)
            }
        }
    }
}

