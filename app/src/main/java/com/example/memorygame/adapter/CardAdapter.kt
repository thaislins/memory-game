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
    context: Context,
    private val resource: Int,
    private val cardList: MutableList<Card>?
) : ArrayAdapter<CardAdapter.CardHolder>(context, resource), AdapterItemsContract {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val card = cardList?.get(position)
        var convView = convertView

        val holder: CardHolder = CardHolder()
        if (convertView == null) {
            convView = LayoutInflater.from(context).inflate(resource, null)
            holder.cardImagePhotoPath = convView?.findViewById(R.id.ivCardGame)

            holder.cardImagePhotoPath?.let {
                Glide.with(context)
                    .load(card?.image?.src)
                    .into(it)
            };

            /*holder.cardImagePhotoPath?.background =
                context.resources.getDrawable(R.drawable.card_front)*/
        }

        return convView!!
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