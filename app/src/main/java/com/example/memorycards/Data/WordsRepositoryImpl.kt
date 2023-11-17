package com.example.memorycards.Data

import com.example.memorycards.Domain.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class WordsRepositoryImpl @Inject constructor(
    private val dao: WordsDao
) : WordsRepository {
    override fun getWords(): List<Word> {
        return dao.getAll().map { it.asDomain() }
    }

    override fun addWord(word: Word) {
        dao.insert(word.asRoom())
    }

    override fun update(word: Word) {
        dao.update(word.asRoom())
    }

    override fun delete(wordId: Int) {
        dao.delete(wordId)
    }

    override fun observeWords(): Flow<List<Word>> {
        return dao.observeAll()
            .map { listItems -> listItems.map { item -> item.asDomain() } }
    }
}