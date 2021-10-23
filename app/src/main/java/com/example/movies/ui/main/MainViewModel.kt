package com.example.movies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.ResourceProvider
import com.example.movies.data.Success
import com.example.movies.domain.Movie
import com.example.movies.domain.MovieRepository
import java.lang.Error

class MainViewModel(private val resourceProvider: ResourceProvider,private val repository:MovieRepository) : ViewModel() {

    private val _loadingLiveData = MutableLiveData(false)
    private val _errorLiveData = MutableLiveData<String>()
    private val _movieLiveData = MutableLiveData<Movie?>()

    val loadingLiveData: LiveData<Boolean> = _loadingLiveData
    val errorLiveData: LiveData<String> = _errorLiveData
    val movieLiveData: LiveData<Movie?> = _movieLiveData

    fun fetchMovie(){
        _loadingLiveData.value = true
        repository.getMovie {
            when(it) {
                is Success ->{
                    _movieLiveData.value = it.value
                }
                is Error ->{
                    _errorLiveData.value = it.printStackTrace().toString()

                }
            }
        }
        _loadingLiveData.value = false
    }

    override fun onCleared() {
        super.onCleared()
        repository.shutDown()
    }
}