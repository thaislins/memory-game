package com.example.memorygame.modules.game.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memory_game.modules.card.model.Card
import com.example.memory_game.modules.product.model.Image
import com.example.memory_game.modules.product.model.Product
import com.example.memory_game.modules.product.viewmodel.ProductViewModel
import com.example.memorygame.modules.game.model.datasource.GameDataSourceImp
import com.example.memorygame.modules.game.model.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class GameViewModel : ViewModel(), KoinComponent {

    val repository by lazy {
        GameRepository(GameDataSourceImp())
    }

    val cards = MutableLiveData<List<Card>>().apply { value = emptyList() }

    fun getImageList(products: List<Product>?): MutableList<Image> {
        val images = mutableListOf<Image>()
        if (products != null) {
            for (product in products) {
                images.add(product.image!!)
            }
        }

        return images
    }

    fun createCards(pairs: Int, amountMatches: Int, productViewModel: ProductViewModel) {
        viewModelScope.launch(Dispatchers.IO) {
            productViewModel.loadProducts()
            val images = getImageList(productViewModel.products.value)
            cards.value = repository.showCards(
                pairs, amountMatches,
                listOf<Image>(images[0], images[1], images[2])
            )
        }
    }
}