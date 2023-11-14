package com.example.memorycards.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.memorycards.Data.Models.WordRoom
import com.example.memorycards.Data.WordsDao

@Database(
    version = 1,
    entities = [WordRoom::class]
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun wordsDao(): WordsDao

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java, "database",
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}
