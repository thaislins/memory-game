package com.example.memorygame.modules.game.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.memory_game.modules.card.model.Card
import com.example.memorygame.R

class CardAdapter(
    context: Context,
    private val resource: Int,
    private val cardList: MutableList<Card>?
) : ArrayAdapter<CardAdapter.CardHolder>(context, resource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        val holder: CardHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null)
            holder = CardHolder()
            holder.cardImagePhotoPath = convertView.findViewById(R.id.ivCard)
            convertView!!.tag = holder
        } else {
            holder = convertView.tag as CardHolder
        }

        return convertView
    }

    override fun getCount(): Int {
        return if (this.cardList != null) this.cardList.size else 0
    }

    class CardHolder {
        internal var cardImagePhotoPath: ImageView? = null
    }
}