package com.example.memorycards.Presentation.EditModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorycards.Data.WordsRepository
import com.example.memorycards.Domain.Word
import com.example.memorycards.items.WordItem
import com.example.memorycards.items.asItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditModuleViewModel @Inject constructor(
     private val repository: WordsRepository
) : ViewModel() {

    private val state = MutableStateFlow<List<WordItem>>(emptyList())

    fun uistate() = state.asStateFlow()

    init {
        viewModelScope.launch {
            repository.observeWords().collect() {
                state.value = it.map { it.asItem() }
            }
        }
    }
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