package com.example.movies.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.data.storage.entities.MovieCategoryEntity
import com.example.movies.data.storage.entities.MovieDetailsEntity
import com.example.movies.data.storage.entities.MovieEntity

@Database(entities = [MovieEntity::class, MovieDetailsEntity::class, MovieCategoryEntity::class], version = 2)
abstract class MovieDataBase :RoomDatabase(){
    abstract fun moviesDao():MoviesDao
}