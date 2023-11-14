package com.example.memorycards.Data.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("words")
data class WordRoom(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("Слово") val name: String,
    @ColumnInfo("Перевод") val translation: String
)