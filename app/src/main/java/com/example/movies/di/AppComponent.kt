package com.example.movies.di

import com.example.movies.di.modules.ApplicationModule
import com.example.movies.di.modules.RepositoryModule
import com.example.movies.ui.main.MainActivity
import com.example.movies.ui.main.list.MoviesListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class,RepositoryModule::class])
interface AppComponent {
    fun mainSubcomponent():MainSubcomponent.Factory
}