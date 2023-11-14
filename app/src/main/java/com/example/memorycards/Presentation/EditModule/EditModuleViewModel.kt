package com.example.memorycards.Presentation.EditModule

import androidx.lifecycle.ViewModel
import com.example.memorycards.Data.WordsRepository
import com.example.memorycards.Domain.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditModuleViewModel @Inject constructor(
     private val repository: WordsRepository
) : ViewModel() {
    fun clickSave(name: String, translation: String) {

        val word = Word(name = name, translation = translation)
        CoroutineScope(Dispatchers.IO).launch {
            repository.addWord(word)

            withContext(Dispatchers.Main) {
            }
        }
    }
    fun getWord() : List<Word> {
        return repository.getWords()
    }
    fun deleteWord(wordId : Int) {
        repository.delete(wordId)
    }
}