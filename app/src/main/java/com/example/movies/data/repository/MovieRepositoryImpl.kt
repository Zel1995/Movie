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
        const val NOW_PLAYING_KEY = "now_playing"
        const val TOP_RATED_KEY = "top_rated"
        const val UPCOMING_KEY = "upcoming"
        const val RU_LANGUAGE_KEY = "ru"
    }

    override suspend fun getMovies(): Flow<RepositoryResult<List<MovieCategory>>> = flow {
        val categoriesList = listOf(
            POPULAR_KEY,
            NOW_PLAYING_KEY,
            TOP_RATED_KEY,
            UPCOMING_KEY
        )
/*        getCachedMoviesByCategories(categoriesList)?.let {
            emit(Success(it))
        }*/
        try {

            val result = getMoviesByCategories(categoriesList)
            //moviesDao.clearMovies()
          /*  result.forEach {
                moviesDao.addMovies(movieEntityMapper.toMovieEntityList(it.result, POPULAR_KEY))
            }*/
            emit(Success(result))
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

    private suspend fun getCachedMoviesByCategories(categoriesList: List<String>): List<MovieCategory>? {
        return categoriesList.map {
            MovieCategory(
                it,
                movieEntityMapper.toMovieList(moviesDao.getMoviesByCategory(it)) ?: listOf()
            )
        }
    }

    private suspend fun getMoviesByCategories(categoriesList: List<String>): List<MovieCategory> {
        return categoriesList.map {
            val response = movieApi.getMovies(it, BuildConfig.TMDB_KEY, RU_LANGUAGE_KEY)
            movieApiResponseMapper.toMovieCategoryList(it, response)
        }
    }

}

sealed class RepositoryResult<T>
data class Success<T>(val value: T) : RepositoryResult<T>()
data class Error<T>(val error: Throwable) : RepositoryResult<T>()