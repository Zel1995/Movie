package com.example.movies.ui.main.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.repository.Success
import com.example.movies.domain.MovieRepository
import com.example.movies.domain.model.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(private val repository:MovieRepository): ViewModel() {
    private val _movie = MutableStateFlow<MovieDetails?>(null)
    private val _error = MutableSharedFlow<String>()
    private val _loading = MutableStateFlow<Boolean>(false)

    val movie = _movie.asStateFlow()
    val error = _error.asSharedFlow()
    val loading = _loading.asStateFlow()

    fun fetchMovie(id:Int){
        viewModelScope.launch{
            repository.getMovie(id)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _loading.value = true
                }.collect {result ->
                    when(result){
                        is Success ->{
                            _movie.value = result.value
                        }
                        is Error ->{
                            _error.emit(result.printStackTrace().toString())
                        }
                    }
                    _loading.value = false

                }
        }
    }
}