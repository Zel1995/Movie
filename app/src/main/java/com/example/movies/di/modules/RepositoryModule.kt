package com.example.movies.di.modules

import com.example.movies.data.mapper.*
import com.example.movies.data.network.ActorsApi
import com.example.movies.data.network.MovieApi
import com.example.movies.data.repository.ActorsRepositoryImpl
import com.example.movies.data.repository.FavoriteMovieRepositoryImpl
import com.example.movies.data.repository.MovieRepositoryImpl
import com.example.movies.data.storage.FavoriteMoviesDao
import com.example.movies.data.storage.MoviesDao
import com.example.movies.domain.repository.ActorsRepository
import com.example.movies.domain.repository.FavoriteMovieRepository
import com.example.movies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun providesRepository(
        movieApi: MovieApi,
        moviesDao: MoviesDao,
        movieApiResponseMapper: MovieApiResponseMapper,
        movieEntityMapper: MovieEntityMapper,
        videosResponseMapper: VideosResponseMapper
    ): MovieRepository =
        MovieRepositoryImpl(
            movieApi,
            moviesDao,
            movieApiResponseMapper,
            movieEntityMapper,
            videosResponseMapper
        )

    @Provides
    @Singleton
    fun providesFavoriteMovieRepository(
        favoriteMoviesDao: FavoriteMoviesDao,
        favoriteMovieEntityMapper: FavoriteMovieEntityMapper
    ): FavoriteMovieRepository {
        return FavoriteMovieRepositoryImpl(favoriteMoviesDao, favoriteMovieEntityMapper)
    }

    @Provides
    @Singleton
    fun providesActorsRepository(
        actorsApi: ActorsApi,
        actorsResponseMapper: ActorsResponseMapper
    ): ActorsRepository =
        ActorsRepositoryImpl(actorsApi, actorsResponseMapper)

}