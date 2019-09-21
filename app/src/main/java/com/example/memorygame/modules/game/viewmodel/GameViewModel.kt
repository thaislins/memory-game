package com.example.memorygame.modules.game.viewmodel

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
        images = images.shuffled().subList(0, amountMatches)
        cards.value = repository.showCards(pairs, amountMatches, images)
    }
}