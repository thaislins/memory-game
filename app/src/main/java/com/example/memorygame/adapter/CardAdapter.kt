package com.example.memorygame.adapter

import android.animation.AnimatorInflater
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

    lateinit var holder: CardHolder
    var changedPositions = setOf<Int>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convView = convertView

        if (convertView == null) {
            convView = LayoutInflater.from(context).inflate(resource, null)
            holder = CardHolder()
            holder.cardImagePhotoPath = convView?.findViewById(R.id.ivCardGame)
            convView!!.tag = holder
        } else {
            holder = convertView.tag as CardHolder
        }

        checksCard(position)
        return convView!!
    }

    private fun checksCard(position: Int) {
        val card = cardList?.get(position)
        // Checks if card is face up or down to define its content
        if (card?.isMatched!!) {
            hideCard()
        } else {
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

    private fun hideCard() {
        holder.cardImagePhotoPath?.visibility = View.INVISIBLE
    }

    private fun showBack() {
        holder.cardImagePhotoPath?.setImageDrawable(context.resources.getDrawable((R.drawable.ic_card_back)))
        holder.cardImagePhotoPath?.background =
            context.resources.getDrawable((R.drawable.card_back))
    }

    fun showFront(card: Card) {
        holder.cardImagePhotoPath?.let { Glide.with(context).load(card.image?.src).into(it) };
        holder.cardImagePhotoPath?.background = context.resources.getDrawable(R.drawable.card_front)
    }

    private fun flipAnimation(res: Int) {
        val flip = AnimatorInflater.loadAnimator(context, res)
        flip.setTarget(holder.cardImagePhotoPath)
        flip.start()
    }

    override fun replaceItems(list: List<*>) {
        cardList?.addAll(list as List<Card>)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return cardList?.size ?: 0
    }

    class CardHolder {
        internal var cardImagePhotoPath: ImageView? = null
    }
}