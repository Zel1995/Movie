package com.example.movies.data.repository

import com.example.movies.BuildConfig
import com.example.movies.data.network.MovieApi
import com.example.movies.domain.MovieRepository
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MovieCategory

class MovieRepositoryImpl(val movieApi: MovieApi) : MovieRepository {
    companion object {
        const val POPULAR_KEY = "popular"
        const val RU_LANGUAGE_KEY = "ru"
    }

    override suspend fun getMovies(): RepositoryResult<List<MovieCategory>> {
        try {
            val response = movieApi.getMovies(POPULAR_KEY, BuildConfig.TMDB_KEY, RU_LANGUAGE_KEY)
            val movies = response.result.map {
                Movie(
                    it.id,
                    it.title
                )
            }
            return Success(listOf(MovieCategory(POPULAR_KEY,movies)))
        } catch (exc: Exception){
            return Error(exc)
        }
    }
}

sealed class RepositoryResult<T>
data class Success<T>(val value: T) : RepositoryResult<T>()
data class Error<T>(val error: Throwable) : RepositoryResult<T>()