package com.example.movies.domain.repository

import com.example.movies.data.repository.RepositoryResult
import com.example.movies.domain.model.movie.MovieCategory
import com.example.movies.domain.model.movie.MovieDetails
import com.example.movies.domain.model.video.Videos
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<RepositoryResult<List<MovieCategory>>>
    fun getMovie(id: Int): Flow<RepositoryResult<MovieDetails>>
    fun getVideo(id: Int): Flow<RepositoryResult<Videos>>
    fun searchMovies(query: String): Flow<RepositoryResult<MovieCategory>>

}