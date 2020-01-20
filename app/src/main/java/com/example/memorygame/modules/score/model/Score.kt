package com.example.memorygame.modules.score.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Score {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var name: String = ""
    var score: Int = 0

    constructor(name: String, score: Int) {
        this.name = name
        this.score = score
    }
}