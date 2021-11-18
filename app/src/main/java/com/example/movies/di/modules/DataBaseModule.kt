package com.example.movies.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.movies.data.storage.FavoriteMoviesDao
import com.example.movies.data.storage.MovieDataBase
import com.example.movies.data.storage.MoviesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun providesMovieDataBase(app: Application): MovieDataBase {
        return Room.databaseBuilder(app, MovieDataBase::class.java, "MovieDataBase")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    fun providesMoviesDao(dataBase: MovieDataBase):MoviesDao = dataBase.moviesDao()


    @Provides
    fun providesFavoriteMoviesDao(dataBase: MovieDataBase):FavoriteMoviesDao = dataBase.favoriteMoviesDao()

}