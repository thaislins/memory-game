package com.example.memorygame.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.memory_game.modules.card.model.Card
import com.example.memorygame.R

class CardAdapter(
    context: Context, private val resource: Int, private val cardList: MutableList<Card>?
) : ArrayAdapter<CardAdapter.CardHolder>(context, resource), AdapterItemsContract {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val card = cardList?.get(position)
        var convView = convertView

        val holder: CardHolder
        if (convertView == null) {
            convView = LayoutInflater.from(context).inflate(resource, null)
            holder = CardHolder()
            holder.cardImagePhotoPath = convView?.findViewById(R.id.ivCardGame)
            convView!!.tag = holder
        } else {
            holder = convertView.tag as CardHolder
        }

        checksCard(card, holder)
        return convView!!
    }

    private fun checksCard(card: Card?, holder: CardHolder) {
        // Checks if card is face up or down to define its content
        if (card?.isFaceUp!!) {
            showCard(card, holder)
        } else {
            hideCard(card, holder)
        }
    }

    private fun hideCard(card: Card, holder: CardAdapter.CardHolder) {
        holder.cardImagePhotoPath?.setImageDrawable(context.resources.getDrawable((R.drawable.ic_card_back)))
        holder.cardImagePhotoPath?.background =
            context.resources.getDrawable((R.drawable.card_back))
    }

    fun showCard(card: Card?, holder: CardHolder) {
        holder.cardImagePhotoPath?.let { Glide.with(context).load(card?.image?.src).into(it) };
        holder.cardImagePhotoPath?.background = context.resources.getDrawable(R.drawable.card_front)
    }

    override fun replaceItems(list: List<*>) {
        cardList?.addAll(list as List<Card>)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return if (this.cardList != null) this.cardList.size else 0
    }

    class CardHolder {
        internal var cardImagePhotoPath: ImageView? = null
    }
}