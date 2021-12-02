package com.example.movies.di

import com.example.movies.di.modules.MainActivityModule
import com.example.movies.di.modules.RouterModule
import com.example.movies.ui.main.MainActivity
import com.example.movies.ui.main.actor.ActorFragment
import com.example.movies.ui.main.actors.ActorsFragment
import com.example.movies.ui.main.categories.MoviesListFragment
import com.example.movies.ui.main.details.MovieFragment
import com.example.movies.ui.main.favorite.FavoriteFragment
import com.example.movies.ui.main.search.SearchMoviesFragment
import dagger.Subcomponent

@Subcomponent(modules = [RouterModule::class, MainActivityModule::class])
interface MainSubcomponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(mainActivityModule: MainActivityModule): MainSubcomponent
    }

    fun inject(main: MainActivity)
    fun inject(moviesListFragment: MoviesListFragment)
    fun inject(movieFragment: MovieFragment)
    fun inject(favoriteFragment: FavoriteFragment)
    fun inject(actorsFragment: ActorsFragment)
    fun inject(searchMovieFragment: SearchMoviesFragment)
    fun inject(actorFragment: ActorFragment)
}