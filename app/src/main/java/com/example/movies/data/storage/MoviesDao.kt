package com.example.movies.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.data.storage.entities.list.MovieCategoryEntity
import com.example.movies.data.storage.entities.list.MovieCategoryWithMovies
import com.example.movies.data.storage.entities.list.MovieEntity

@Dao
interface MoviesDao {

    @Query("SELECT name,page,totalPages FROM MovieCategoryEntity")
    suspend fun getMovieCategoryWithMovies(): List<MovieCategoryWithMovies>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMoviesCategory(movies: MovieCategoryEntity)

    @Query("DELETE FROM MovieCategoryEntity")
    suspend fun clearMovieCategories()

    @Query("DELETE FROM MovieEntity")
    suspend fun clearMoviesEntity()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(movieCategoryEntity: MovieCategoryEntity)
}