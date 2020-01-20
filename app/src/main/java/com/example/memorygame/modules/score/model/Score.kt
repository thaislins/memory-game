package com.example.memorygame.modules.score.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Score() {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0
    var playerName: String? = ""
    var value: Int? = 0
}