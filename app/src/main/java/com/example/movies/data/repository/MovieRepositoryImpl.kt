package com.example.movies.data.repository

import com.example.movies.BuildConfig
import com.example.movies.data.mapper.MovieApiResponseMapper
import com.example.movies.data.mapper.MovieEntityMapper
import com.example.movies.data.network.MovieApi
import com.example.movies.data.storage.MoviesDao
import com.example.movies.domain.MovieRepository
import com.example.movies.domain.model.MovieCategory
import com.example.movies.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val moviesDao: MoviesDao,
    private val movieApiResponseMapper: MovieApiResponseMapper,
    private val movieEntityMapper: MovieEntityMapper
) :
    MovieRepository {
    companion object {
        const val POPULAR_KEY = "popular"
        const val RU_LANGUAGE_KEY = "ru"
    }

    override fun getMovies(): Flow<RepositoryResult<List<MovieCategory>>> = flow {
        val movies = moviesDao.getMoviesByCategory(POPULAR_KEY)
        movies?.let { cachedValue ->
            val movieCategory =
                MovieCategory(POPULAR_KEY, movieEntityMapper.toMovieList(cachedValue))
            emit(Success(listOf(movieCategory)))
        }
        try {
            val response = movieApi.getMovies(POPULAR_KEY, BuildConfig.TMDB_KEY, RU_LANGUAGE_KEY)
            val result = response.result.map{movieApiResponseMapper.toMovie(it)}
            moviesDao.addMovie(movieEntityMapper.toMovieEntityList(result, POPULAR_KEY))
            emit(Success(listOf(MovieCategory(POPULAR_KEY, result))))
        } catch (exc: Exception) {
            emit(Error(exc))
        }
    }

    override fun getMovie(id: Int): Flow<RepositoryResult<MovieDetails>> = flow {
        try {
            val response = movieApi.getMovie(id, BuildConfig.TMDB_KEY, RU_LANGUAGE_KEY)
            val movie = movieApiResponseMapper.toMovieDetails(response)
            emit(Success(movie))
        } catch (exc: Exception) {
            emit(Error(exc))
        }
    }
}

sealed class RepositoryResult<T>
data class Success<T>(val value: T) : RepositoryResult<T>()
data class Error<T>(val error: Throwable) : RepositoryResult<T>()