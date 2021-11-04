package com.example.movies.di.modules

import com.example.movies.BuildConfig
import com.example.movies.data.network.MovieApi
import com.example.movies.data.repository.MovieRepositoryImpl
import com.example.movies.data.storage.MoviesDao
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
        MovieRepositoryImpl(movieApi, moviesDao)

    @Provides
    fun providesMovieApi(okHttpClient: OkHttpClient): MovieApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    fun providesLoginInterceptor(): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build()
}