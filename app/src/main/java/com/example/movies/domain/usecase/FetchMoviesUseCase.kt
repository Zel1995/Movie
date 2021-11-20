package com.example.movies.domain.usecase

import com.example.movies.data.repository.RepositoryResult
import com.example.movies.data.repository.Success
import com.example.movies.domain.model.movie.MovieCategory
import com.example.movies.domain.repository.MovieRepository
import com.example.movies.ui.main.categories.AdultsStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val adultsStorage: AdultsStorage
) {
    fun run(): Flow<RepositoryResult<List<MovieCategory>>> {
        return if (adultsStorage.isAdults) {
            repository.getMovies()
        } else {
            repository.getMovies().filter {
                if (it is Success) {
                    it.value.filter {
                        it.results.filter { !it.adult }
                        true
                    }
                }
                true
            }
        }
    }
}