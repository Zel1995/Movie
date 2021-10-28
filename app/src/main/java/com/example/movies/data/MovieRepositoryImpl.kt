package com.example.movies.data

import android.os.Handler
import android.os.Looper
import com.example.movies.domain.model.Movie
import com.example.movies.domain.MovieRepository
import java.util.concurrent.Executor

class MovieRepositoryImpl : MovieRepository {
    private val handler = Handler(Looper.getMainLooper())

    override fun getMovies(
        executor: Executor,
        callback: (result: RepositoryResult<List<Movie>>) -> Unit
    ) {
        executor.execute {
            Thread.sleep(1000)
            if (true) {
                handler.post {
                    callback(Success(listOf(Movie("0", "name,"))))
                }
            } else {
                handler.post {
                    callback(Error(RuntimeException("Exc")))
                }
            }
        }
    }
}

sealed class RepositoryResult<T>
data class Success<T>(val value: T) : RepositoryResult<T>()
data class Error<T>(val error: Throwable) : RepositoryResult<T>()