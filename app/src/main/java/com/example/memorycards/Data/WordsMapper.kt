package com.example.memorycards.Data

import com.example.memorycards.Data.Models.WordRoom
import com.example.memorycards.Domain.Word

fun WordRoom.asDomain(): Word {
    return Word(
        id = id,
        name = name,
        translation = translation
    )
}

fun Word.asRoom(): WordRoom {
    return WordRoom(
        id = id,
        name = name,
        translation = translation
    )
}