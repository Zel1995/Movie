package com.example.movies.domain.repository

import com.example.movies.data.repository.RepositoryResult
import com.example.movies.domain.model.movie.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
    fun getFavoriteMovies(): Flow<RepositoryResult<List<Movie>>>
    suspend fun deleteFavoriteMovieById(id: Int)
    suspend fun addFavoriteMovie(movie: Movie)
    suspend fun getFavoriteMovieById(id: Int): Movie?
}