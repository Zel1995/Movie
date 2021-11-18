package com.example.movies.domain

import com.example.movies.data.repository.RepositoryResult
import com.example.movies.domain.model.MovieCategory
import com.example.movies.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<RepositoryResult<List<MovieCategory>>>
    fun getMovie(id: Int): Flow<RepositoryResult<MovieDetails>>
}