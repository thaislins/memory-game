package com.example.memorygame.adapter

import android.widget.GridView
import androidx.databinding.BindingAdapter

class BindingAdapters {

    companion object {
        @BindingAdapter("cards")
        @JvmStatic
        fun setCards(gridView: GridView, items: List<Any>) {

            gridView.adapter.let {
                if (it is AdapterItemsContract) {
                    it.replaceItems(items)
                }
            }
        }
    }
}

