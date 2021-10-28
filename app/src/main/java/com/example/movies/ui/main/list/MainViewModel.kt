package com.example.movies.ui.main.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.ResourceProvider
import com.example.movies.data.Success
import com.example.movies.domain.model.Movie
import com.example.movies.domain.MovieRepository
import com.example.movies.domain.model.MovieCategory
import java.util.concurrent.Executors

class MainViewModel(
    private val resourceProvider: ResourceProvider,
    private val repository: MovieRepository
) : ViewModel() {
    private val executor = Executors.newSingleThreadExecutor()

    private val _loadingLiveData = MutableLiveData(false)
    private val _errorLiveData = MutableLiveData<String>()
    private val _movieLiveData = MutableLiveData<List<MovieCategory>>()

    val loadingLiveData: LiveData<Boolean> = _loadingLiveData
    val errorLiveData: LiveData<String> = _errorLiveData
    val movieLiveData: LiveData<List<MovieCategory>> = _movieLiveData

    fun fetchMovie() {
        _loadingLiveData.value = true
        repository.getMovies(executor) {
            when (it) {
                is Success -> {
                    val result = it.value
                    _movieLiveData.value = result
                }
                is Error -> {
                    _errorLiveData.value = it.printStackTrace().toString()

                }
            }
        }
        _loadingLiveData.value = false
    }

    override fun onCleared() {
        super.onCleared()
        executor.shutdown()
    }
}