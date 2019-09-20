package com.example.memory_game

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.memory_game.modules.card.model.Card
import com.example.memory_game.modules.product.viewmodel.ProductViewModel
import com.example.memorygame.R
import com.example.memorygame.adapter.CardAdapter
import com.example.memorygame.databinding.ActivityMainBinding
import com.example.memorygame.modules.game.viewmodel.GameViewModel
import org.koin.android.ext.android.inject

class GameActivity : AppCompatActivity() {

    var cards = mutableListOf<Card>()
    val productViewModel: ProductViewModel by inject()
    val gameViewModel: GameViewModel? by lazy {
        ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val adapter = CardAdapter(this, R.layout.item_card, cards)

            val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)
            binding.gridView.adapter = adapter
            binding.viewModel = gameViewModel
            binding.viewModel?.createCards(2, 2, productViewModel)
        } catch (ex: Exception) {
            Log.e("ERROROROROR", ex.message)
        }
    }
}
