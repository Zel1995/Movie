package com.example.movies.di

import com.example.movies.di.modules.MainActivityModule
import com.example.movies.di.modules.RouterModule
import com.example.movies.ui.main.MainActivity
import com.example.movies.ui.main.list.MoviesListFragment
import dagger.Subcomponent

@Subcomponent(modules = [RouterModule::class,MainActivityModule::class])
interface MainSubcomponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(mainActivityModule: MainActivityModule):MainSubcomponent
    }
    fun inject(main: MainActivity)
    fun inject(moviesListFragment: MoviesListFragment)
}