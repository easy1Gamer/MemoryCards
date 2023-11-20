package com.example.memorycards.Presentation.EditModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorycards.Data.WordsRepository
import com.example.memorycards.Domain.Word
import com.example.memorycards.items.WordItem
import com.example.memorycards.items.asDomain
import com.example.memorycards.items.asItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditModuleViewModel @Inject constructor(
     private val repository: WordsRepository
) : ViewModel() {

    private val state = MutableStateFlow(DataState())

    fun uistate() = state.asStateFlow()

    init {
        viewModelScope.launch {
            repository.observeWords().collect() { items ->
                state.update { it.copy(items = items.map { it.asItem() }) }
            }
        }
    }

    fun clickSave(name: String, translation: String, pictureUrl: String, id: Int) {
        state.value.selectedItem?.let{
            val newWord = it.copy(name = name, translation = translation, pictureUrl = pictureUrl)
            viewModelScope.launch {
                repository.update(newWord.asDomain())
            }
        } ?: run {
            val word = Word(name = name, translation = translation, pictureUrl = pictureUrl)
            CoroutineScope(Dispatchers.IO).launch {
                repository.addWord(word)
            }
        }
        state.update { it.copy(selectedItem = null) }
    }
    fun deleteWord(wordId : Int) {
        repository.delete(wordId)
    }

    fun onClickFill(wordItem: WordItem) {
        state.update { it.copy(selectedItem = wordItem) }
    }
}

data class DataState(
    val items:List<WordItem> = emptyList(),
    val selectedItem:WordItem? = null
)