package com.example.movies.ui.main.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.R
import com.example.movies.data.repository.Error
import com.example.movies.data.repository.Success
import com.example.movies.domain.model.movie.MovieCategory
import com.example.movies.domain.usecase.FetchMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val adultsStorage: AdultsStorage
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    private val _error = MutableSharedFlow<String>()
    private val _movie = MutableStateFlow<List<MovieCategory>>(listOf())
    private val _iconMode = MutableStateFlow(R.drawable.ic_done)

    val loading: StateFlow<Boolean> = _loading
    val error: SharedFlow<String> = _error
    val movie: StateFlow<List<MovieCategory>> = _movie
    val iconMode: StateFlow<Int> = _iconMode

    init {
        val adults = adultsStorage.isAdults
        val icon = if (adults) R.drawable.ic_done else R.drawable.ic_done
        _iconMode.value = icon
    }

    fun fetchMovie() {
        viewModelScope.launch {
            fetchMoviesUseCase.run()
                .flowOn(Dispatchers.IO)
                .onStart{
                    _loading.value = true
                }.collect { result ->
                    when (result) {
                        is Success -> {
                            val movies = result.value
                            _movie.value = movies
                        }
                        is Error -> {
                            _error.emit(result.error.printStackTrace().toString())
                        }
                    }
                    _loading.value = false
                }
        }
    }

    fun onAdultsChanged() {
        val adults = adultsStorage.isAdults.not()
        adultsStorage.isAdults = adults

        val icon = if (adults) R.drawable.ic_done else R.drawable.ic_done
        _iconMode.value = icon
    }
}