package com.example.movies.domain

import com.example.movies.data.repository.RepositoryResult
import com.example.movies.data.storage.entities.FavoriteMovieEntity
import com.example.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
    fun getFavoriteMovies(): Flow<RepositoryResult<List<Movie>>>
    suspend fun deleteFavoriteMovieById(id:Int)
    suspend fun addFavoriteMovie(movie:Movie)
    suspend fun getFavoriteMovieById(id:Int):Movie?
}