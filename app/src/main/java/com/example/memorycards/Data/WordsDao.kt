package com.example.memorycards.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.memorycards.Data.Models.WordRoom

@Dao
interface WordsDao {

    @Query("Select * from words")
    fun getAll(): List<WordRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: WordRoom)

    @Update
    fun update(word: WordRoom)

    @Query("Delete from words where id = :wordId ")
    fun delete(wordId: Int)
}