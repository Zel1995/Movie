package com.example.movies.domain

import com.example.movies.data.repository.RepositoryResult
import com.example.movies.domain.model.MovieCategory
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<RepositoryResult<List<MovieCategory>>>
}