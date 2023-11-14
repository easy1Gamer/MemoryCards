package com.example.memorycards.Presentation.Resources

import com.example.memorycards.Domain.Word

data class WordItem(
    val id: Int = 0,
    val name: String,
    val translation: String,
    var isTranslate: Boolean = false
)

fun Word.asItem() = WordItem(id, name, translation)