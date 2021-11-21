package com.example.movies.ui.main.favorite

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movies.R
import com.example.movies.databinding.FragmentFavoriteBinding
import com.example.movies.domain.model.movie.Movie
import com.example.movies.domain.repository.FavoriteMovieRepository
import com.example.movies.ui.main.MainActivity
import com.example.movies.ui.main.router.MainRouter
import com.example.movies.ui.main.viewBinding
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    companion object {
        fun newInstance() = FavoriteFragment()
    }
    private val adapter = FavoriteMoviesAdapter(::onItemClick){
        viewModel.deleteMovie(it)
    }
    private fun onItemClick(movie:Movie){
        router.openMovieDetailsFragment(movie)
    }
    @Inject
    lateinit var router: MainRouter
    @Inject
    lateinit var factory: FavoriteMoviesViewModelFactory
    private val viewModel: FavoriteMoviesViewModel by viewModels {
        factory
    }
    private val viewBinding: FragmentFavoriteBinding by viewBinding(FragmentFavoriteBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerViewFavorite.adapter = adapter
        viewModel.fetchMovies()
        initViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? MainActivity)?.mainSubcomponent?.inject(this)

    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.movie.collect {
                adapter.setData(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loading.collect {

            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.emptyScreen.collect {
                viewBinding.emptyFavoriteImageView.visibility = if(it) View.VISIBLE else View.GONE
                viewBinding.addFavoriteMovieTextView.visibility = if(it) View.VISIBLE else View.GONE
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.error.collect {

            }
        }
    }
}

class FavoriteMoviesViewModelFactory @Inject constructor(private val repository: FavoriteMovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteMoviesViewModel(repository) as T
    }

}