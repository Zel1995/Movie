package com.example.movies.ui.main.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.repository.Error
import com.example.movies.data.repository.Success
import com.example.movies.domain.model.actor.ActorDetails
import com.example.movies.domain.repository.ActorsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ActorViewModel(private val repository: ActorsRepository) : ViewModel() {
    private val _actor = MutableStateFlow<ActorDetails?>(null)
    private val _loading = MutableStateFlow(false)
    private val _error = MutableSharedFlow<String>()

    val actor = _actor.asStateFlow()
    val loading = _loading.asStateFlow()
    val error = _error.asSharedFlow()

    fun fetchActor(id: String) {
        viewModelScope.launch {
            repository.getActor(id)
                .onStart { _loading.value = true }
                .collect {
                    when (it) {
                        is Success -> {
                            _actor.value = it.value
                        }
                        is Error -> {
                            _error.emit(it.error.stackTraceToString())
                        }
                    }
                }
        }
    }
}