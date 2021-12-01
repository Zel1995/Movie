package com.example.movies.data.repository

import android.net.Uri
import com.example.movies.BuildConfig
import com.example.movies.data.mapper.MovieApiResponseMapper
import com.example.movies.data.mapper.MovieEntityMapper
import com.example.movies.data.mapper.VideosResponseMapper
import com.example.movies.data.network.MovieApi
import com.example.movies.data.storage.MoviesDao
import com.example.movies.domain.model.movie.MovieCategory
import com.example.movies.domain.model.movie.MovieDetails
import com.example.movies.domain.model.video.Videos
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val moviesDao: MoviesDao,
    private val movieApiResponseMapper: MovieApiResponseMapper,
    private val movieEntityMapper: MovieEntityMapper,
    private val videosResponseMapper: VideosResponseMapper
) :
    MovieRepository {
    companion object {
        const val POPULAR_KEY = "popular"
        const val NOW_PLAYING_KEY = "now_playing"
        const val TOP_RATED_KEY = "top_rated"
        const val UPCOMING_KEY = "upcoming"
        const val RU_LANGUAGE_KEY = "ru"
    }

    override fun getMovies(): Flow<RepositoryResult<List<MovieCategory>>> = flow {
        val categoriesList = listOf(
            POPULAR_KEY,
            NOW_PLAYING_KEY,
            TOP_RATED_KEY,
            UPCOMING_KEY
        )
        val cacheResult = moviesDao.getMovieCategoryWithMovies()?.map {
            movieEntityMapper.toMovieCategory(it)
        }
        cacheResult?.let {
            emit(Success(it))
        }
        try {
            val result = getMoviesByCategoriesFromApi(categoriesList)
            moviesDao.clearMovieCategories()
            moviesDao.clearMoviesEntity()
            result.forEach {
                moviesDao.addCategory(movieEntityMapper.toMovieCategoryEntity(it))
                moviesDao.addMovies(movieEntityMapper.toMovieEntityList(it.results))
            }
            emit(Success(result))
        } catch (exc: Exception) {
            emit(Error<List<MovieCategory>>(exc))
        }
    }

    override fun getMovie(id: Int): Flow<RepositoryResult<MovieDetails>> = flow {
        try {
            val response = movieApi.getMovie(id, BuildConfig.TMDB_KEY, RU_LANGUAGE_KEY)
            val movie = movieApiResponseMapper.toMovieDetails(response)
            emit(Success(movie))
        } catch (exc: Exception) {
            emit(Error<MovieDetails>(exc))
        }
    }

    override fun getVideo(id: Int): Flow<RepositoryResult<Videos>> = flow{
        try {
            val response = movieApi.getVideo(id,BuildConfig.TMDB_KEY, RU_LANGUAGE_KEY)
            emit(Success(videosResponseMapper.toVideos(response)))
        }catch (exc:Exception){
            emit(Error<Videos>(exc))
        }
    }

    override fun searchMovies(query: String): Flow<RepositoryResult<MovieCategory>> = flow {
        try {
            val result =
                movieApi.searchMovies(BuildConfig.TMDB_KEY, RU_LANGUAGE_KEY, Uri.parse(query))
            emit(Success(movieApiResponseMapper.toMovieCategory(result)))
        } catch (exc: Exception) {
            emit(Error<MovieCategory>(exc))
        }
    }

    private suspend fun getMoviesByCategoriesFromApi(categoriesList: List<String>): List<MovieCategory> {
        return categoriesList.map {
            val response = movieApi.getMovies(it, BuildConfig.TMDB_KEY, RU_LANGUAGE_KEY)
            movieApiResponseMapper.toMovieCategory(response, it)
        }
    }

}

sealed class RepositoryResult<T>
data class Success<T>(val value: T) : RepositoryResult<T>()
data class Error<T>(val error: Throwable) : RepositoryResult<T>()