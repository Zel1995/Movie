package com.example.movies.ui.main.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.R
import com.example.movies.data.repository.Error
import com.example.movies.data.repository.Success
import com.example.movies.domain.model.movie.Movie
import com.example.movies.domain.model.movie.MovieDetails
import com.example.movies.domain.model.video.Videos
import com.example.movies.domain.repository.FavoriteMovieRepository
import com.example.movies.domain.repository.MovieRepository
import com.example.movies.domain.usecase.AddOrDeleteFavoriteMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository,
    private val favoriteRepository: FavoriteMovieRepository,
    private val addOrDeleteFavoriteMovieUseCase: AddOrDeleteFavoriteMovieUseCase
) : ViewModel() {
    private val _movie = MutableStateFlow<MovieDetails?>(null)
    private val _error = MutableSharedFlow<String>()
    private val _loading = MutableStateFlow<Boolean>(false)
    private val _favoriteIcon = MutableStateFlow(R.drawable.ic_like)
    private val _video = MutableStateFlow<Videos?>(null)

    val movie = _movie.asStateFlow()
    val error = _error.asSharedFlow()
    val loading = _loading.asStateFlow()
    val favoriteIcon = _favoriteIcon.asStateFlow()
    val video = _video.asStateFlow()

    fun fetchMovie(id: Int) {
        viewModelScope.launch {
            checkFavorite(id)
            repository.getMovie(id)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _loading.value = true
                }.collect { result ->
                    when (result) {
                        is Success -> {
                            _movie.value = result.value
                        }
                        is Error -> {
                            _error.emit(result.error.stackTraceToString())
                        }
                    }
                    _loading.value = false
                }
        }
    }

    fun fetchVideo(id: Int) {
        viewModelScope.launch {
            repository.getVideo(id)
                .collect {
                    when (it) {
                        is Success -> {
                            _video.value = it.value
                        }
                        is Error -> {
                            _error.emit(it.error.stackTraceToString())
                        }
                    }
                }
        }

    }

    fun addOrDeleteFavoriteMovie(movie: Movie) {
        viewModelScope.launch {
            _favoriteIcon.value = addOrDeleteFavoriteMovieUseCase.run(movie)
        }
    }


    suspend fun checkFavorite(id: Int) {
        _favoriteIcon.value =
            if (favoriteRepository.getFavoriteMovieById(id) != null) R.drawable.ic_heart else R.drawable.ic_like
    }
}