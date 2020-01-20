package com.example.memorygame.modules.score.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorygame.modules.score.model.Score
import com.example.memorygame.modules.score.model.repository.ScoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ScoreViewModel : ViewModel(), KoinComponent {

    private val repository: ScoreRepository by inject()
    val scores = MutableLiveData<MutableList<Score>>().apply { value = null }

    fun loadScoreList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var list = repository.loadAll()
                list = list?.sortedBy { it.value }
                scores.postValue(list?.reversed() as MutableList<Score>?)
            } catch (ex: Exception) {
                Log.e("Error", ex.message)
            }
        }
    }
}