package com.example.memorygame.modules.game.viewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.home.model.Image
import com.example.memorygame.modules.home.model.Product
import com.example.memorygame.exceptions.CardAlreadySelectedException
import com.example.memorygame.modules.game.model.datasource.GameDataSourceImp
import com.example.memorygame.modules.game.model.repository.GameRepository
import org.koin.core.KoinComponent

class GameViewModel : ViewModel(), KoinComponent {

    private val posFirstCardFaceUp = MutableLiveData<Int>().apply { value = -1 }
    private val posSecondCardFaceUp = MutableLiveData<Int>().apply { value = -1 }
    val updateLayout = MutableLiveData<Boolean>().apply { value = false }
    private val delay: Long = 500
    val cards = MutableLiveData<List<Card>>().apply { value = emptyList() }

    private val repository by lazy {
        GameRepository(GameDataSourceImp())
    }

    private fun getImageList(products: List<Product>?): List<Image> {
        return products?.map { it.image!! } ?: listOf()
    }

    fun createCards(pairs: Int, amountMatches: Int, products: List<Product>?) {
        var images = getImageList(products)
        images = images.shuffled().take(pairs)
        cards.value = repository.showCards(pairs, amountMatches, images)
    }

    fun chooseCard(position: Int) {
        // Makes sure you cannot select the same card twice and when two cards are clicked a third can't be clicked
        if (position == posFirstCardFaceUp.value || posSecondCardFaceUp.value != -1) {
            throw CardAlreadySelectedException()
        }
        cards.value?.also {
            // Makes sure you cannot select an already matched card
            if (it[position].isMatched) {
                throw CardAlreadySelectedException()
            }
            // Checks if there already is one card up
            if (!it[position].isMatched && posFirstCardFaceUp.value != -1) {
                posSecondCardFaceUp.value = position
                it[position].isFaceUp = true
                updateLayout.value = true

                Handler().postDelayed({
                    updateLayout.value = false
                    checkIfCardsMatch()
                }, delay)

                // Makes sure that if a card is clicked more than once that it can't match itself
            } else {
                posFirstCardFaceUp.value = position
                it[position].isFaceUp = true
                updateLayout.value = true
            }
        }
    }

    private fun checkIfCardsMatch() {
        val firstCardPos = posFirstCardFaceUp.value!!
        val secondCardPos = posSecondCardFaceUp.value!!

        cards.value?.also {
            if (it[firstCardPos].id == it[secondCardPos].id) {
                it[firstCardPos].isMatched = true
                it[secondCardPos].isMatched = true
            }
            it[firstCardPos].isFaceUp = false
            it[secondCardPos].isFaceUp = false
        }

        posFirstCardFaceUp.value = -1
        posSecondCardFaceUp.value = -1
        updateLayout.value = true
    }
}