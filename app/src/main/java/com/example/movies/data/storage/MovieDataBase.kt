package com.example.movies.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.data.storage.entities.FavoriteMovieEntity
import com.example.movies.data.storage.entities.MovieCategoryEntity
import com.example.movies.data.storage.entities.MovieDetailsEntity
import com.example.movies.data.storage.entities.MovieEntity

@Database(
    entities = [MovieEntity::class, MovieDetailsEntity::class, MovieCategoryEntity::class,FavoriteMovieEntity::class],
    version = 4
)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun favoriteMoviesDao(): FavoriteMoviesDao
}