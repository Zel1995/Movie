package com.example.movies.ui.main.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.repository.Success
import com.example.movies.domain.MovieRepository
import com.example.movies.domain.model.MovieCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)
    private val _movie = MutableStateFlow<List<MovieCategory>>(listOf())

    val loading: StateFlow<Boolean> = _loading
    val error: StateFlow<String?> = _error
    val movie: StateFlow<List<MovieCategory>> = _movie

    fun fetchMovie() {
        _loading.value = true
        viewModelScope.launch {
            when (val result = repository.getMovies()) {
                is Success -> {
                    val movies = result.value
                    _movie.value = movies
                    _error.value = null
                }
                is Error -> {
                    _error.value = result.printStackTrace().toString()

                }
            }
            _loading.value = false
        }
    }
}