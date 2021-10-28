package com.example.movies.di.modules

import com.example.movies.data.MovieRepositoryImpl
import com.example.movies.domain.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(): MovieRepository = MovieRepositoryImpl()
}