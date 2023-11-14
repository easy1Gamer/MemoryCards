package com.example.memorycards.Application

import android.app.Application
import androidx.room.Room
import com.example.memorycards.Database.AppDataBase
import com.example.memorycards.di.AppComponent
import com.example.memorycards.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().application(this).build()

    }

    fun getAppComponent() : AppComponent = appComponent

    companion object {
        lateinit var appComponent: AppComponent
        private set
    }
}
