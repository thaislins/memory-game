package com.example.memorygame.modules.game.viewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorygame.exceptions.CardAlreadySelectedException
import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.game.model.Game
import com.example.memorygame.modules.game.model.repository.GameRepository
import com.example.memorygame.modules.home.model.Image
import com.example.memorygame.modules.home.model.Product
import com.example.memorygame.modules.score.model.Score
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class GameViewModel : ViewModel(), KoinComponent {

    private val repository: GameRepository by inject()
    var gameOver = MutableLiveData<Boolean>().apply { value = false }
    val cardsFaceUp = MutableLiveData<HashMap<Int, Card>>().apply { value = hashMapOf() }
    val score = MutableLiveData<Int>().apply { value = 0 }
    val updateLayout = MutableLiveData<Boolean>().apply { value = false }
    val matchedCardCount = MutableLiveData<Int>().apply { value = 0 }
    val cards = MutableLiveData<MutableList<Card>>().apply { value = mutableListOf() }

    private fun getImageList(products: List<Product>?): List<Image> {
        return products?.map { it.image } as List<Image>? ?: listOf()
    }

    private fun initializeValues() {
        Card.identifierFactory = 0
        matchedCardCount.value = 0
        gameOver.value = false
        score.value = 0
        cards.value?.clear()
    }

    /**
     * Create card layout and shuffle them
     *
     */
    fun createCards(amountOfPairs: Int, amountEqualCards: Int, products: List<Product>?) {
        initializeValues()
        var images = getImageList(products)
        images = images.shuffled().take(amountOfPairs)
        cards.value = repository.showCards(amountOfPairs, amountEqualCards, images)
    }

    /**
     * Increments score according to how much time has elapsed in the game
     *
     */
    fun addToScore(elapsedTime: Long) {
        if (matchedCardCount.value == 0) {
            score.value = 0
        } else {
            val timeMargin1 = 10000 * Game.amountEqualCards
            val timeMargin2 = 15000 * Game.amountEqualCards
            val timeMargin3 = 25000 * Game.amountEqualCards

            if (elapsedTime <= timeMargin1) score.value = score.value?.plus(50)
            else if (elapsedTime > timeMargin1 && elapsedTime <= timeMargin2) score.value =
                score.value?.plus(30)
            else if (elapsedTime > timeMargin2 && elapsedTime <= timeMargin3) score.value =
                score.value?.plus(20)
            else score.value = score.value?.plus(10)
        }
    }

    fun chooseCard(position: Int) {
        if (cardsFaceUp.value?.containsKey(position)!!) { // Makes sure you cannot select the same card twice and when two cards are clicked a third can't be clicked
            throw CardAlreadySelectedException()
        }

        cards.value?.also {
            if (it[position].isMatched) { // Makes sure a matched card cannot be selected
                throw CardAlreadySelectedException()
            } else {
                if (cardsFaceUp.value?.size!! >= 0 && cardsFaceUp.value!!.size < Game.amountEqualCards) {
                    it[position].isFaceUp = true
                    cardsFaceUp.value?.put(position, it[position])
                    updateLayout.value = true

                    if (cardsFaceUp.value?.size == Game.amountEqualCards) {
                        Handler().postDelayed({
                            updateLayout.value = false
                            checkIfCardsMatch()
                        }, 500)
                    }
                }
            }
        }
    }

    /**
     * Checks all values in cardsFaceUp map to see if they match
     *
     */
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
                } else {
                    it[key].isFaceUp = false
                }
            }

        }

        if (cardsMatch) matchedCardCount.value = matchedCardCount.value?.plus(1)
        checkIfGameOver()
        cardsFaceUp.value?.clear()
        updateLayout.value = true
    }

    fun checkIfGameOver() { // Checks if amount of matches
        matchedCardCount.value.also {
            if (it != 0 && it == (cards.value?.size?.div(Game.amountEqualCards))) {
                gameOver.value = true
            }
        }
    }

    fun saveScore() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveScore(
                Score(
                    Game.playerName,
                    score.value!!
                )
            )
        }
    }
}