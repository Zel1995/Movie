package com.example.movies.di

import android.app.Application
import com.example.movies.di.modules.ApplicationModule

class App : Application() {
    val appComponent =
        DaggerAppComponent.builder().applicationModule(ApplicationModule(this)).build()
}