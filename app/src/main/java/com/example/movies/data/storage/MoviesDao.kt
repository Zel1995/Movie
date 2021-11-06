package com.example.movies.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MovieEntity WHERE category = :category")
    suspend fun getMoviesByCategory(category: String): List<MovieEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM MovieEntity")
    suspend fun clearMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieDetails: MovieDetailsEntity)

}