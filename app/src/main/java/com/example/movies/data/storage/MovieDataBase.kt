package com.example.movies.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.data.storage.entities.list.FavoriteMovieEntity
import com.example.movies.data.storage.entities.list.MovieCategoryEntity
import com.example.movies.data.storage.entities.details.MovieDetailsEntity
import com.example.movies.data.storage.entities.list.MovieEntity

@Database(
    entities = [MovieEntity::class, MovieDetailsEntity::class, MovieCategoryEntity::class, FavoriteMovieEntity::class],
    version = 4
)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun favoriteMoviesDao(): FavoriteMoviesDao
}