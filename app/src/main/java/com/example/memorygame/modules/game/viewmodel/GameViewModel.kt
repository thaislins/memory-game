package com.example.memorygame.modules.game.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memory_game.modules.card.model.Card
import com.example.memorygame.modules.game.model.datasource.GameDataSourceImp
import com.example.memorygame.modules.game.model.repository.GameRepository

class GameViewModel : ViewModel() {

    val repository by lazy {
        GameRepository(GameDataSourceImp())
    }

    val cards = MutableLiveData<List<Card>>().apply { value = emptyList() }

    fun createCards(pairs: Int, amountMatches: Int) {
        cards.value = repository.showCards(pairs, amountMatches)
    }
}