package com.example.movies.ui.main.details

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieBinding
import com.example.movies.domain.MovieRepository
import com.example.movies.domain.model.Movie
import com.example.movies.ui.main.MainActivity
import com.example.movies.ui.main.list.MoviesAdapter.Companion.BASE_IMAGE_URL
import com.example.movies.ui.main.viewBinding
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


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

    @Inject
    lateinit var factory: MovieViewModelFactory

    private val viewModel: MovieViewModel by viewModels {
        factory
    }
    val viewBinding: FragmentMovieBinding by viewBinding(FragmentMovieBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        val movie = arguments?.let { it.getParcelable(MOVIE_ARG) as Movie? }
        movie?.id?.let { viewModel.fetchMovie(it) }
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.error.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loading.collect {
                viewBinding.progress.visibility = if (it) View.VISIBLE else View.GONE
                viewBinding.movieLayout.visibility = if (it) View.GONE else View.VISIBLE
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.movie.collect {
                Glide.with(viewBinding.moviePoserImageView)
                    .load(BASE_IMAGE_URL + it?.backdropPath)
                    .into(viewBinding.moviePoserImageView)

                viewBinding.moviePoserImageView
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? MainActivity)?.mainSubcomponent?.inject(this)

    }
}

class MovieViewModelFactory @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepository) as T
    }
}