package com.example.movies.ui.main.router

import androidx.fragment.app.FragmentManager
import com.example.movies.R
import com.example.movies.domain.model.movie.Movie
import com.example.movies.ui.main.actors.ActorsFragment
import com.example.movies.ui.main.details.MovieFragment
import com.example.movies.ui.main.favorite.FavoriteFragment
import com.example.movies.ui.main.categories.MoviesListFragment
import com.example.movies.ui.main.search.SearchMoviesFragment

class MainRouter(private val fragmentManager: FragmentManager) {
    fun openMoviesListFragment() {
        fragmentManager.beginTransaction().replace(
            R.id.container, MoviesListFragment()
        ).commit()
    }

    fun openMovieDetailsFragment(movie: Movie) {
        fragmentManager.beginTransaction().addToBackStack("details")
            .replace(R.id.container, MovieFragment.newInstance(movie))
            .commit()
    }
    fun openFavoriteFragment() {
        fragmentManager.beginTransaction().addToBackStack("favorite")
            .replace(R.id.container, FavoriteFragment.newInstance())
            .commit()
    }

    fun openActorsFragment() {
        fragmentManager.beginTransaction().addToBackStack("actors")
            .replace(R.id.container, ActorsFragment())
            .commit()
    }
    fun openSearchFragment() {
        fragmentManager.beginTransaction().addToBackStack("search")
            .replace(R.id.container, SearchMoviesFragment())
            .commit()
    }
}