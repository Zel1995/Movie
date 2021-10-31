package com.example.movies.ui.main.list

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieListBinding
import com.example.movies.di.App
import com.example.movies.domain.MovieRepository
import com.example.movies.ui.main.MainActivity
import com.example.movies.ui.main.router.MainRouter
import com.example.movies.ui.main.viewBinding
import javax.inject.Inject

class MoviesListFragment : Fragment(R.layout.fragment_movie_list) {
    private val adapter = MovieCategoriesAdapter {
        mainRouter.openMovieDetailsFragment(it)
        }


    @Inject
    lateinit var factory: MainViewModelFactory
    @Inject
    lateinit var mainRouter: MainRouter
    private val viewBinding: FragmentMovieListBinding by viewBinding(FragmentMovieListBinding::bind)
    private val viewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.fetchMovie()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as? MainActivity)?.mainSubcomponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelLiveData()
        initViews()
        viewBinding.rvMovieCategories.adapter = adapter
    }

    private fun initViews() {
        viewBinding.swipeRefresh.setOnRefreshListener { viewModel.fetchMovie() }
    }

    private fun initViewModelLiveData() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            viewBinding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.movieLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}

class MainViewModelFactory @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(movieRepository) as T
    }

}