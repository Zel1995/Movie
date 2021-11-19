package com.example.movies.di.modules

import com.example.movies.BuildConfig
import com.example.movies.data.mapper.ActorsResponseMapper
import com.example.movies.data.mapper.FavoriteMovieEntityMapper
import com.example.movies.data.mapper.MovieApiResponseMapper
import com.example.movies.data.mapper.MovieEntityMapper
import com.example.movies.data.network.ActorsApi
import com.example.movies.data.network.MovieApi
import com.example.movies.data.repository.ActorsRepositoryImpl
import com.example.movies.data.repository.FavoriteMovieRepositoryImpl
import com.example.movies.data.repository.MovieRepositoryImpl
import com.example.movies.data.storage.FavoriteMoviesDao
import com.example.movies.data.storage.MoviesDao
import com.example.movies.domain.ActorsRepository
import com.example.movies.domain.FavoriteMovieRepository
import com.example.movies.domain.MovieRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun providesRepository(movieApi: MovieApi, moviesDao: MoviesDao): MovieRepository =
        MovieRepositoryImpl(movieApi, moviesDao, MovieApiResponseMapper(), MovieEntityMapper())

    @Provides
    @Singleton
    fun providesFavoriteMovieRepository(favoriteMoviesDao: FavoriteMoviesDao):FavoriteMovieRepository{
        return FavoriteMovieRepositoryImpl(favoriteMoviesDao, FavoriteMovieEntityMapper())
    }
    @Provides
    @Singleton
    fun providesActorsRepository(actorsApi: ActorsApi):ActorsRepository =
        ActorsRepositoryImpl(actorsApi, ActorsResponseMapper())

}