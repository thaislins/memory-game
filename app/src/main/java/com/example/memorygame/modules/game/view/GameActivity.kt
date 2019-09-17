package com.example.memory_game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.memory_game.modules.card.model.Card
import com.example.memory_game.modules.product.viewmodel.ProductViewModel
import com.example.memorygame.R
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity() {

    var cards = mutableListOf<Card>()

    val viewModel: ProductViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.loadProducts()
        createCards()
    }

    fun createCards() {
        for (i in 1..4) {
            val card = Card()
            cards.addAll(Arrays.asList(card, card))
        }
        var newCards = cards.shuffled()
    }
}
