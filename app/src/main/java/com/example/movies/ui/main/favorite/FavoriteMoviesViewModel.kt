package com.example.movies.ui.main.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.repository.Error
import com.example.movies.data.repository.Success
import com.example.movies.domain.FavoriteMovieRepository
import com.example.movies.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel(private val repository: FavoriteMovieRepository) : ViewModel() {
    private val _movie = MutableStateFlow<List<Movie>>(listOf())
    private val _error = MutableSharedFlow<String>()
    private val _loading = MutableStateFlow(false)
    private val _emptyScreen = MutableStateFlow(false)

    val movie = _movie.asStateFlow()
    val error = _error.asSharedFlow()
    val loading = _loading.asStateFlow()
    val emptyScreen = _emptyScreen.asStateFlow()

    fun fetchMovies() {
        viewModelScope.launch {

            repository.getFavoriteMovies()
                .flowOn(Dispatchers.IO)
                .onStart { _loading.value = true }
                .collect {
                    when (it) {
                        is Success -> {
                            _emptyScreen.value = it.value.isEmpty()
                            _movie.value = it.value
                        }
                        is Error -> {
                            _error.emit(it.error.message.toString())
                        }
                    }
                    _loading.value = false
                }
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            repository.deleteFavoriteMovieById(movie.id)
        }
    }
}