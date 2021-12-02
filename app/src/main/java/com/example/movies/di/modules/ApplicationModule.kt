package com.example.movies.di.modules

import android.app.Application
import android.content.Context
import com.example.movies.di.App
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val app: App) {
    @Provides
    fun providesApplication(): Application = app

    @Provides
    fun providesContext(): Context = app
}