package com.example.memorycards.di

import android.content.Context
import com.example.memorycards.Presentation.EditModule.EditModuleViewModel
import com.example.memorycards.Presentation.Modules.ModuleViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DatabaseModule::class]
)
interface AppComponent {
    fun editModuleVIewModel() : EditModuleViewModel
    fun getModuleVIewModel() : ModuleViewModel
    @Component.Builder
    interface AppComponentBuilder {

        fun application(@BindsInstance application : Context) : AppComponentBuilder

        fun build() : AppComponent
    }
}