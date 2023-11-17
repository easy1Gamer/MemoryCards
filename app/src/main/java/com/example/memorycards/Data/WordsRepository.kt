package com.example.memorycards.Data

import com.example.memorycards.Domain.Word
import kotlinx.coroutines.flow.Flow

interface WordsRepository {
    fun getWords(): List<Word>
    fun addWord(word: Word)
    fun update(word: Word)
    fun delete(wordId: Int)
    fun observeWords(): Flow<List<Word>>
}