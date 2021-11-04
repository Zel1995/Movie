package com.example.movies.data.repository

import com.example.movies.BuildConfig
import com.example.movies.data.network.MovieApi
import com.example.movies.data.storage.MovieEntity
import com.example.movies.data.storage.MoviesDao
import com.example.movies.domain.MovieRepository
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MovieCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val movieApi: MovieApi, private val moviesDao: MoviesDao) :
    MovieRepository {
    companion object {
        const val POPULAR_KEY = "popular"
        const val RU_LANGUAGE_KEY = "ru"
    }

    override fun getMovies(): Flow<RepositoryResult<List<MovieCategory>>> = flow {
        val movies = moviesDao.getMoviesByCategory(POPULAR_KEY)
        movies?.let { cachedValue ->
            val movieCategory = MovieCategory(POPULAR_KEY, cachedValue.map { Movie(it.id, it.name) })
            emit(Success(listOf(movieCategory)))
        }
        try {
            val response = movieApi.getMovies(POPULAR_KEY, BuildConfig.TMDB_KEY, RU_LANGUAGE_KEY)
            val movies = response.result.map {
                Movie(
                    it.id,
                    it.title
                )
            }
            val moviesEntity = response.result.map {
                MovieEntity(
                    it.id,
                    POPULAR_KEY,
                    it.title,
                )
            }
            emit(Success(listOf(MovieCategory(POPULAR_KEY, movies))))
        } catch (exc: Exception) {
            emit(Error(exc))
        }
    }
}

sealed class RepositoryResult<T>
data class Success<T>(val value: T) : RepositoryResult<T>()
data class Error<T>(val error: Throwable) : RepositoryResult<T>()