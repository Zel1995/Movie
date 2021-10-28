package com.example.movies.di.modules

import android.app.Application
import com.example.movies.di.App
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val app:App) {
    @Provides
    fun provideApplication(): Application = app
}