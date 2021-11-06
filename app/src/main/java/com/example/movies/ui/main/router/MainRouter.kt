package com.example.movies.ui.main.router

import androidx.fragment.app.FragmentManager
import com.example.movies.R
import com.example.movies.domain.model.Movie
import com.example.movies.ui.main.details.MovieFragment
import com.example.movies.ui.main.list.MoviesListFragment

class MainRouter(private val fragmentManager: FragmentManager) {
    fun openMoviesListFragment() {
        fragmentManager.beginTransaction().replace(
            R.id.container, MoviesListFragment()
        ).commit()
    }

    fun openMovieDetailsFragment(movie: Movie) {
        fragmentManager.beginTransaction().addToBackStack("details").replace(R.id.container, MovieFragment.newInstance(movie))
            .commit()
    }
}