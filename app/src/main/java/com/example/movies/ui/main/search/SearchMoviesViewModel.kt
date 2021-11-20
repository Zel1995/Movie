package com.example.movies.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.repository.Error
import com.example.movies.data.repository.Success
import com.example.movies.domain.model.movie.MovieCategory
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchMoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _searchMovies = MutableStateFlow<MovieCategory?>(null)
    private val _error = MutableSharedFlow<String>()
    private val _loading = MutableStateFlow(false)

    val searchMovies = _searchMovies.asStateFlow()
    val error = _error.asSharedFlow()
    val loading = _loading.asStateFlow()

    fun fetchSearchResult(query: String) {
        viewModelScope.launch {
            repository.searchMovies(query)
                .onStart { _loading.value = true }
                .collect {
                    when (it) {
                        is Success -> {
                            _searchMovies.value = it.value
                        }
                        is Error -> {
                            _error.emit(it.error.message.toString())
                        }
                    }
                    _loading.value = false
                }
        }
    }


}