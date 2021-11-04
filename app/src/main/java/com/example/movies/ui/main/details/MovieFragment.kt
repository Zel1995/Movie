package com.example.movies.ui.main.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieBinding
import com.example.movies.domain.model.Movie
import com.example.movies.ui.main.list.MoviesViewModel
import com.example.movies.ui.main.viewBinding


class MovieFragment : Fragment(R.layout.fragment_movie) {
    companion object {
        private const val MOVIE_ARG = "MOVIE_ARG"

        fun newInstance(movie: Movie): MovieFragment {
            return MovieFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE_ARG, movie)
                }
            }
        }
    }

    private val viewModel: MoviesViewModel by viewModels {
        MovieViewModelFactory()
    }
    val viewBinding: FragmentMovieBinding by viewBinding(FragmentMovieBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}

class MovieViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel() as T
    }
}