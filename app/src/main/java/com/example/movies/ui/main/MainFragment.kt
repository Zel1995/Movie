package com.example.movies.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.R
import com.example.movies.ResourceProvider
import com.example.movies.ViewBindingDelegate
import com.example.movies.data.MovieRepositoryImpl
import com.example.movies.databinding.MainFragmentBinding
import com.example.movies.domain.MovieRepository
import com.example.movies.viewBinding

class MainFragment : Fragment(R.layout.main_activity) {

    companion object {
        fun newInstance() = MainFragment()
    }
    private val viewBinding :MainFragmentBinding by viewBinding(MainFragmentBinding::bind)
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(ResourceProvider(requireActivity().application),MovieRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            viewModel.fetchMovie()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModelLiveData()
    }

    private fun initViewModelLiveData() {
        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner){}
        viewModel.movieLiveData.observe(viewLifecycleOwner){}
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}

class MainViewModelFactory(
    private val resourceProvider: ResourceProvider,
    private val movieRepository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(resourceProvider, movieRepository) as T
    }

}