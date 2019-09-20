package com.example.memory_game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.memory_game.modules.card.model.Card
import com.example.memory_game.modules.product.viewmodel.ProductViewModel
import com.example.memorygame.R
import com.example.memorygame.modules.game.model.adapter.CardAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.*


class GameActivity : AppCompatActivity() {

    val viewModel: ProductViewModel by inject()
    var cards = mutableListOf<Card>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.loadProducts()
        createCards(2, 2)
        val adapter = CardAdapter(this, R.layout.item_card, cards)
        gridView.adapter = adapter
    }

    fun createCards(pairs: Int, amountMatches: Int) {
        for (i in 1..pairs) {
            val card = Card()
            cards.addAll(MutableList(amountMatches) {card})
        }
        cards = cards.shuffled() as MutableList<Card>
    }
}
