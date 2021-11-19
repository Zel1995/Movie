package com.example.movies.ui.main.actors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.repository.Success
import com.example.movies.domain.ActorsRepository
import com.example.movies.domain.model.Actors
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ActorsViewModel(private val repository: ActorsRepository) : ViewModel() {
    private val _actor = MutableStateFlow<Actors?>(null)
    private val _loading = MutableStateFlow(false)
    private val _error = MutableSharedFlow<String>()

    val actor = _actor.asStateFlow()
    val loading = _loading.asStateFlow()
    val error = _error.asSharedFlow()

    fun fetchActors() {
        viewModelScope.launch {
            repository.getActors()
                .onStart { _loading.value = true }
                .collect {
                    when (it) {
                        is Success -> {
                            _actor.value = it.value
                        }
                        is Error -> {
                            _error.emit(it.message.toString())
                        }
                    }
                    _loading.value = false
                }
        }
    }
}