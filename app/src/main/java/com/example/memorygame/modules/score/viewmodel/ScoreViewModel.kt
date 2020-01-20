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
    val scores = MutableLiveData<List<Score>>().apply { value = null }

    fun loadScoreList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                scores.postValue(repository.loadAll())
            } catch (ex: Exception) {
                Log.e("Error", ex.message)
            }
        }
    }
}