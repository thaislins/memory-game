package com.example.memorygame.modules.game.view.adapter

import android.animation.AnimatorInflater
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.memorygame.R
import com.example.memorygame.modules.game.model.Card

class CardAdapter(
    context: Context, private val resource: Int, private var cardList: MutableList<Card>?
) : ArrayAdapter<CardAdapter.CardHolder>(context, resource), AdapterCardsContract {

    private lateinit var holder: CardHolder
    var changedPositions = setOf<Int>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val convView: View

        if (convertView == null) {
            convView = LayoutInflater.from(context).inflate(resource, null)
            holder = CardHolder()
            holder.cardImagePhotoPath = convView?.findViewById(R.id.ivCardGame)
            convView.tag = holder
        } else {
            holder = convertView.tag as CardHolder
            convView = convertView
        }

        checksCard(position)
        return convView
    }

    private fun checksCard(position: Int) {
        val card = cardList?.get(position)
        // Checks if card is face up or down to define its content
        if (!card?.isMatched!!) {
            if (card.isFaceUp) {
                if (changedPositions.contains(position)) {
                    flipAnimation(R.animator.card_flip_right_out)
                    flipAnimation(R.animator.card_flip_left_in)
                }
                showFront(card)
            } else {
                if (changedPositions.contains(position)) {
                    flipAnimation(R.animator.card_flip_left_out)
                    flipAnimation(R.animator.card_flip_right_in)
                }
                showBack()
            }
        }
    }

    private fun showBack() {
        holder.cardImagePhotoPath?.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_card_back
            )
        )
        holder.cardImagePhotoPath?.background =
            ContextCompat.getDrawable(context, R.drawable.card_back)
    }

    private fun showFront(card: Card) {
        holder.cardImagePhotoPath?.let { Glide.with(context).load(card.image?.src).into(it) }
        holder.cardImagePhotoPath?.background =
            ContextCompat.getDrawable(context, R.drawable.card_front)
    }

    private fun flipAnimation(res: Int) {
        val flip = AnimatorInflater.loadAnimator(context, res)
        flip.setTarget(holder.cardImagePhotoPath)
        flip.start()
    }

    override fun setCardList(list: MutableList<Card>) {
        cardList?.clear()
        cardList?.addAll(list)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return cardList?.size ?: 0
    }

    class CardHolder {
        internal var cardImagePhotoPath: ImageView? = null
    }
}