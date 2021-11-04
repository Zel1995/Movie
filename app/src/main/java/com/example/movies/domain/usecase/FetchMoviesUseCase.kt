package com.example.movies.domain.usecase

import com.example.movies.data.repository.RepositoryResult
import com.example.movies.domain.MovieRepository
import com.example.movies.domain.model.MovieCategory
import com.example.movies.ui.main.list.AdultsStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val adultsStorage: AdultsStorage
) {
    suspend fun run(): Flow<RepositoryResult<List<MovieCategory>>>{
        return if(adultsStorage.isAdults){
            repository.getMovies()
        }else{
            repository.getMovies()
        }

    }
}