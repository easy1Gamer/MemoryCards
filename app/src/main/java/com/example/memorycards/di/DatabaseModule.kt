package com.example.memorycards.di

import android.content.Context
import com.example.memorycards.Data.WordsDao
import com.example.memorycards.Data.WordsRepository
import com.example.memorycards.Data.WordsRepositoryImpl
import com.example.memorycards.Database.AppDataBase
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {
    @Provides
    fun provideAppDataBase (context : Context): AppDataBase {
        return AppDataBase.getInstance(context)
    }

    @Provides
    fun provideWordsDao (appDataBase: AppDataBase): WordsDao {
        return appDataBase.wordsDao()
    }

    @Provides
    fun provideWordsRepository (impl : WordsRepositoryImpl): WordsRepository {
        return impl
    }
}