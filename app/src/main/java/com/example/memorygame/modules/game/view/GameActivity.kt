package com.example.memory_game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.memory_game.modules.card.model.Card
import com.example.memory_game.modules.product.viewmodel.ProductViewModel
import com.example.memorygame.R
import com.example.memorygame.modules.game.model.adapter.CardAdapter
import com.example.memorygame.modules.game.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class GameActivity : AppCompatActivity() {

    val productViewModel: ProductViewModel by inject()
    val gameViewModel: GameViewModel by inject()
    var cards = mutableListOf<Card>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        productViewModel.loadProducts()
        gameViewModel.createCards(2, 2)
        val adapter = CardAdapter(this, R.layout.item_card, cards)
        gridView.adapter = adapter
    }
}
