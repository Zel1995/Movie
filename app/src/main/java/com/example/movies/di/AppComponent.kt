package com.example.movies.di

import com.example.movies.di.modules.ApplicationModule
import com.example.movies.di.modules.DataBaseModule
import com.example.movies.di.modules.NetworkModule
import com.example.movies.di.modules.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class,RepositoryModule::class,DataBaseModule::class,NetworkModule::class])
interface AppComponent {
    fun mainSubcomponent():MainSubcomponent.Factory
}