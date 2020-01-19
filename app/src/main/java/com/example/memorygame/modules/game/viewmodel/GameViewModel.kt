package com.example.memorygame.modules.game.viewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memorygame.exceptions.CardAlreadySelectedException
import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.game.model.repository.GameRepository
import com.example.memorygame.modules.home.model.Image
import com.example.memorygame.modules.home.model.Product
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*


class GameViewModel : ViewModel(), KoinComponent {

    private val repository: GameRepository by inject()
    val amountOfSets = 2
    val cardsFaceUp = MutableLiveData<HashMap<Int, Card>>().apply { value = hashMapOf() }
    var amountOfMoves = MutableLiveData<Int>().apply { value = 0 }
    val updateLayout = MutableLiveData<Boolean>().apply { value = false }
    var matchedCardCount = MutableLiveData<Int>().apply { value = 0 }
    val cards = MutableLiveData<MutableList<Card>>().apply { value = mutableListOf() }

    private fun getImageList(products: List<Product>?): List<Image> {
        return products?.map { it.image } as List<Image>? ?: listOf()
    }

    private fun initializeValues() {
        Card.identifierFactory = 0
        matchedCardCount.value = 0
        amountOfMoves.value = 0
        cards.value?.clear()
    }

    /**
     * Create card layout and shuffle them
     *
     */
    fun createCards(amountOfPairs: Int, products: List<Product>?) {
        initializeValues()
        var images = getImageList(products)
        images = images.shuffled().take(amountOfPairs)
        cards.value = repository.showCards(amountOfPairs, images)
    }

    /**
     * Shuffles cards when button is clicked
     *
     */
    fun shuffleCards() {
        val newCards = cards.value!!
        newCards.shuffle()
        cards.value = newCards
    }

    fun chooseCard(position: Int) {
        if (cardsFaceUp.value?.containsKey(position)!!) { // Makes sure you cannot select the same card twice and when two cards are clicked a third can't be clicked
            throw CardAlreadySelectedException()
        }

        cards.value?.also {
            if (it[position].isMatched) { // Makes sure a matched card cannot be selected
                throw CardAlreadySelectedException()
            } else {
                //amountOfMoves.value = amountOfMoves.value?.plus(1)
                if (cardsFaceUp.value?.size!! >= 0 && cardsFaceUp.value!!.size < amountOfSets) {
                    it[position].isFaceUp = true
                    cardsFaceUp.value?.put(position, it[position])
                    updateLayout.value = true

                    if (cardsFaceUp.value?.size == amountOfSets) {
                        Handler().postDelayed({
                            updateLayout.value = false
                            checkIfCardsMatch()
                        }, 500)
                    }
                }
            }
        }
    }

    private fun checkIfCardsMatch() {
        val entry = cardsFaceUp.value?.entries?.iterator()?.next()
        var cardsMatch = true

        for (s in cardsFaceUp.value?.values!!) {
            if (!s.id.equals(entry?.value?.id)) {
                cardsMatch = false
            }
        }

        cards.value?.also {
            for ((key, value) in cardsFaceUp.value!!) {
                if (cardsMatch) {
                    it[key].isMatched = true
                    matchedCardCount.value = matchedCardCount.value?.plus(1)
                } else {
                    it[key].isFaceUp = false
                }
            }

        }

        cardsFaceUp.value?.clear()
        updateLayout.value = true
    }
}