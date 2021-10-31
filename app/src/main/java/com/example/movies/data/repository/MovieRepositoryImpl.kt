package com.example.movies.data.repository

import com.example.movies.BuildConfig
import com.example.movies.data.model.TmdbResponse
import com.example.movies.data.network.MovieApi
import com.example.movies.domain.MovieRepository
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MovieCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class MovieRepositoryImpl(val movieApi: MovieApi) : MovieRepository {
    companion object {
        const val POPULAR_KEY = "popular"
        const val RU_LANGUAGE_KEY = "ru"
    }

    override fun getMovies(
        executor: Executor,
        callback: (result: RepositoryResult<List<MovieCategory>>) -> Unit
    ) {
        movieApi.getMovies(POPULAR_KEY, BuildConfig.TMDB_KEY, RU_LANGUAGE_KEY)
            .enqueue(object : Callback<TmdbResponse> {
                override fun onResponse(
                    call: Call<TmdbResponse>,
                    response: Response<TmdbResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val movies = it.result.map {
                                Movie(
                                    it.id,
                                    it.title
                                )
                            }
                            callback.invoke(Success(listOf(MovieCategory(POPULAR_KEY, movies))))
                        }
                    } else {
                        callback.invoke(Error(Exception(response.code().toString())))
                    }
                }

                override fun onFailure(call: Call<TmdbResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

    }
}

sealed class RepositoryResult<T>
data class Success<T>(val value: T) : RepositoryResult<T>()
data class Error<T>(val error: Throwable) : RepositoryResult<T>()