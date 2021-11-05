package com.example.movies.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class,MovieDetailsEntity::class], version = 1)
abstract class MovieDataBase :RoomDatabase(){
    abstract fun moviesDao():MoviesDao
}