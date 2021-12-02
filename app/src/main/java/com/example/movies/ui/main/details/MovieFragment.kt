package com.example.movies.ui.main.details

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.example.movies.domain.model.movie.Movie
import com.example.movies.domain.model.movie.MovieDetails
import com.example.movies.domain.repository.FavoriteMovieRepository
import com.example.movies.domain.repository.MovieRepository
import com.example.movies.domain.usecase.AddOrDeleteFavoriteMovieUseCase
import com.example.movies.ui.main.MainActivity
import com.example.movies.ui.main.UrlDataPath
import com.example.movies.ui.main.viewBinding
import com.google.android.material.chip.Chip
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

    private val adapter = ProductionCompaniesAdapter()

    @Inject
    lateinit var factory: MovieViewModelFactory
    var movie: Movie? = null

    private val viewModel: MovieViewModel by viewModels {
        factory
    }
    val viewBinding: FragmentMovieBinding by viewBinding(FragmentMovieBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        movie = arguments?.getParcelable(MOVIE_ARG)
        movie?.id?.let {
            viewModel.fetchMovie(it)
        }
        viewBinding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        setHasOptionsMenu(true)
        initRecyclerView()
        initFab()
        initMenu()
    }

    private fun initRecyclerView() {
        viewBinding.productionCompaniesRecyclerView.adapter = adapter
    }

    private fun initFab() {
        viewBinding.fab.setOnClickListener {
            movie?.let { movie -> viewModel.fetchVideo(movie.id) }
        }
    }

    private fun initMenu() {
        viewBinding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_like -> {
                    movie?.let { movie -> viewModel.addOrDeleteFavoriteMovie(movie) }
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.error.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loading.collect {
                //TODO loading
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.movie.collect {
                it?.let {
                    initViews(it)
                    adapter.setData(it.productionCompanies.filter { companies -> !companies.logoPath.isNullOrEmpty() })
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.favoriteIcon.collect {
                viewBinding.toolbar.menu.findItem(R.id.item_like).setIcon(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.video.collect {
                it?.let { videos ->
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(UrlDataPath.getYoutubeVideoPath(videos.results[0].key))
                    ).apply {
                        startActivity(this)
                    }
                }
            }
        }
    }

    private fun initViews(it: MovieDetails) {
        Glide.with(viewBinding.movieBackImageView)
            .load(it.backdropPath?.let { UrlDataPath.getBackdropPath(it) })
            .into(viewBinding.movieBackImageView)

        Glide.with(viewBinding.moviePosterImageView)
            .load(it.posterPath?.let { UrlDataPath.getPosterPath(it) })
            .into(viewBinding.moviePosterImageView)

        viewBinding.titleTextView.text = it.title
        viewBinding.tagLineTextView.text = it.tagline

        val runtime = "${getString(R.string.longest)} ${it.runTime} ${getString(R.string.min)}"
        viewBinding.runtimeTextView.text = runtime
        val budget = "${getString(R.string.budget)}  ${it.budget}"
        viewBinding.budgetTextView.text = budget
        val revenue = "${getString(R.string.revenue)} ${it.revenue}"
        viewBinding.revenueTextView.text = revenue
        val status = "${getString(R.string.status)} ${it.status}"
        viewBinding.statusTextView.text = status
        viewBinding.ratingTextView.text = it.voteAverage.toString()
        val releaseDate = "${getString(R.string.release_date)} ${it.releaseDate}"
        viewBinding.releaseDateTextView.text = releaseDate
        viewBinding.ratingBar.rating = it.voteAverage / 2
        viewBinding.overviewTextView.text = it.overview

        it.genres.forEach { genre ->
            val chip = Chip(requireContext())
            chip.text = genre
            viewBinding.genresChipGroup.addView(chip)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? MainActivity)?.mainSubcomponent?.inject(this)
    }
}

class MovieViewModelFactory @Inject constructor(
    private val movieRepository: MovieRepository,
    private val favoriteMovieRepository: FavoriteMovieRepository,
    private val addOrDeleteFavoriteMovieUseCase: AddOrDeleteFavoriteMovieUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(
            movieRepository,
            favoriteMovieRepository,
            addOrDeleteFavoriteMovieUseCase
        ) as T
    }
}