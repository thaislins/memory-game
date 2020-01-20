package com.example.memorygame.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.memorygame.modules.score.model.Score

@Dao
abstract class ScoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insertScore(entity: Score)

    @Query("SELECT * FROM score")
    abstract fun getAll(): List<Score>
}