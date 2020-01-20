package com.example.memorygame.modules.score.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Score(
    var playerName: String?,
    var value: Int?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}