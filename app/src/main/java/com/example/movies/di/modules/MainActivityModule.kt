package com.example.movies.di.modules

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val activity:AppCompatActivity) {
    @Provides
    fun providesActivity() = activity

    @Provides
    fun providesFragmentManager() = activity.supportFragmentManager
}