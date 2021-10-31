package com.example.movies.domain

import com.example.movies.data.repository.RepositoryResult
import com.example.movies.domain.model.MovieCategory

interface MovieRepository {
    suspend fun getMovies(): RepositoryResult<List<MovieCategory>>
}