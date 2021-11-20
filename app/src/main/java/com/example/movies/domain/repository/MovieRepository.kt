package com.example.movies.domain.repository

import com.example.movies.data.repository.RepositoryResult
import com.example.movies.domain.model.movie.MovieCategory
import com.example.movies.domain.model.movie.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<RepositoryResult<List<MovieCategory>>>
    fun getMovie(id: Int): Flow<RepositoryResult<MovieDetails>>
    fun searchMovies(query: String): Flow<RepositoryResult<MovieCategory>>
}