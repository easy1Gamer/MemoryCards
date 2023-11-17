package com.example.memorycards.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.example.memorycards.Data.Models.WordRoom

@Dao
interface WordsDao {

    @Query("SELECT * FROM words")
    fun getAll(): List<WordRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: WordRoom)

    @Update
    fun update(word: WordRoom)

    @Query("DELETE FROM words WHERE id = :wordId ")
    fun delete(wordId: Int)

    @Query("SELECT * FROM words")
    fun observeAll(): Flow<List<WordRoom>>
}