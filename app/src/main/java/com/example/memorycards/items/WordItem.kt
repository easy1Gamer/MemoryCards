package com.example.memorycards.items

import com.example.memorycards.Domain.Word

data class WordItem(
    val id: Int = 0,
    val name: String,
    val translation: String,
    var isTranslate: Boolean = false,
    val pictureUrl: String
)

fun Word.asItem() = WordItem(id, name, translation, pictureUrl = pictureUrl)

fun WordItem.asDomain() = Word(id, name, translation, pictureUrl)