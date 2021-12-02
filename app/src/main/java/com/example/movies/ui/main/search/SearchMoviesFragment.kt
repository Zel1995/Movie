package com.example.movies.ui.main.search

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieSearchBinding
import com.example.movies.domain.repository.MovieRepository
import com.example.movies.ui.main.MainActivity
import com.example.movies.ui.main.router.MainRouter
import com.example.movies.ui.main.viewBinding
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject

class SearchMoviesFragment : Fragment(R.layout.fragment_movie_search) {
    private val adapter = SearchMoviesAdapter {
        router.openMovieDetailsFragment(it)
    }

    @Inject
    lateinit var router: MainRouter

    @Inject
    lateinit var factory: SearchMoviesViewModelFactory
    private val viewBinding: FragmentMovieSearchBinding by viewBinding(FragmentMovieSearchBinding::bind)
    private val viewModel: SearchMoviesViewModel by viewModels { factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchSearchResult(getString(R.string.first_search))
        initViewModel()
        initSearchEdit()
        viewBinding.searchAdapter.adapter = adapter
    }

    private fun initSearchEdit() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            callbackFlow<String> {
                val textWatcher = viewBinding.queryEditText.addTextChangedListener {
                    it?.let { trySend(it.toString()) }
                }
                awaitClose { viewBinding.queryEditText.removeTextChangedListener(textWatcher) }
            }.debounce(500)
                .collect {
                    viewModel.fetchSearchResult(it)
                }
        }
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.searchMovies.collect {
                it?.let { adapter.setData(it.results) }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainSubcomponent?.inject(this)
    }
}

class SearchMoviesViewModelFactory @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchMoviesViewModel(movieRepository) as T
    }
}