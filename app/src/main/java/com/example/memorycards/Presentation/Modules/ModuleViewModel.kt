package com.example.memorycards.Presentation.Modules

import androidx.lifecycle.ViewModel
import com.example.memorycards.Data.WordsRepository
import com.example.memorycards.Domain.Word
import javax.inject.Inject

class ModuleViewModel @Inject constructor(
    private val repository: WordsRepository
) : ViewModel() {
    fun getWord(): List<Word> {
        return repository.getWords()
    }
}