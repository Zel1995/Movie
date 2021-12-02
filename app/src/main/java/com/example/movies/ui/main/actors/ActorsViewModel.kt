package com.example.movies.ui.main.actors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.repository.Error
import com.example.movies.data.repository.Success
import com.example.movies.domain.model.actor.Actors
import com.example.movies.domain.repository.ActorsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ActorsViewModel(private val repository: ActorsRepository) : ViewModel() {
    companion object {
        const val START_PAGE_NUM = 1
    }

    private var currentPage = START_PAGE_NUM
    private val _actors = MutableStateFlow<Actors?>(null)
    private val _loading = MutableStateFlow(false)
    private val _error = MutableSharedFlow<String>()
    private val _page = MutableStateFlow(1)

    val actors = _actors.asStateFlow()
    val loading = _loading.asStateFlow()
    val error = _error.asSharedFlow()
    val page = _page.asStateFlow()

    fun fetchActors(page: Int = currentPage) {
        viewModelScope.launch {
            repository.getActors(currentPage)
                .onStart { _loading.value = true }
                .collect {
                    when (it) {
                        is Success -> {
                            _actors.value = it.value
                        }
                        is Error -> {
                            _error.emit(it.error.message.toString())
                        }
                    }
                    _loading.value = false
                }
        }
    }

    fun nextPage() {
        currentPage += START_PAGE_NUM
        _page.value = currentPage
    }

    fun previousPage() {
        if (currentPage > START_PAGE_NUM) {
            currentPage -= START_PAGE_NUM
            _page.value = currentPage
        }
    }
}