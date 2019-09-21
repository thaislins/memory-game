package com.example.memorygame.modules.game.viewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memory_game.modules.card.model.Card
import com.example.memory_game.modules.product.model.Image
import com.example.memory_game.modules.product.model.Product
import com.example.memorygame.modules.game.model.datasource.GameDataSourceImp
import com.example.memorygame.modules.game.model.repository.GameRepository
import org.koin.core.KoinComponent

class GameViewModel : ViewModel(), KoinComponent {

    val repository by lazy {
        GameRepository(GameDataSourceImp())
    }

    val posFirstCardFaceUp = MutableLiveData<Int>().apply { value = -1 }
    val posSecondCardFaceUp = MutableLiveData<Int>().apply { value = -1 }
    val updateLayout = MutableLiveData<Boolean>().apply { value = false }
    val delay: Long = 600
    val cards = MutableLiveData<List<Card>>().apply { value = emptyList() }

    fun getImageList(products: List<Product>?): List<Image> {
        val images = mutableListOf<Image>()
        if (products != null) {
            for (product in products) {
                images.add(product.image!!)
            }
        }

        return images
    }

    fun createCards(pairs: Int, amountMatches: Int, products: List<Product>?) {
        var images = getImageList(products)
        images = images.shuffled().subList(0, pairs)
        cards.value = repository.showCards(pairs, amountMatches, images)
    }

    fun chooseCard(position: Int) {
        if (posSecondCardFaceUp.value == -1) {
            if (!cards.value?.get(position)?.isMatched!! && posFirstCardFaceUp.value != -1) {
                posSecondCardFaceUp.value = position
                cards.value?.get(position)?.isFaceUp = true
                updateLayout.value = true

                Handler().postDelayed({
                    updateLayout.value = false
                    checkIfCardsMatch()
                }, delay)
            } else {
                posFirstCardFaceUp.value = position
                cards.value?.get(position)?.isFaceUp = true
                updateLayout.value = true
            }
        }
    }

    private fun checkIfCardsMatch() {
        if (cards.value?.get(posFirstCardFaceUp.value!!)?.id == cards.value?.get(posSecondCardFaceUp.value!!)?.id) {
            cards.value?.get(posFirstCardFaceUp.value!!)?.isMatched = true
            cards.value?.get(posSecondCardFaceUp.value!!)?.isMatched = true
        }

        cards.value?.get(posFirstCardFaceUp.value!!)?.isFaceUp = false
        cards.value?.get(posSecondCardFaceUp.value!!)?.isFaceUp = false

        posFirstCardFaceUp.value = -1
        posSecondCardFaceUp.value = -1

        updateLayout.value = true
    }
}