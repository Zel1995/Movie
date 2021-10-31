package com.example.movies.data

import android.os.Handler
import android.os.Looper
import com.example.movies.data.repository.Error
import com.example.movies.data.repository.RepositoryResult
import com.example.movies.data.repository.Success
import com.example.movies.domain.MovieRepository
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MovieCategory
import java.util.concurrent.Executor

class MovieMockRepositoryImpl : MovieRepository {
    private val handler = Handler(Looper.getMainLooper())

    override fun getMovies(
        executor: Executor,
        callback: (result: RepositoryResult<List<MovieCategory>>) -> Unit
    ) {
        executor.execute {
            Thread.sleep(1000)
            if (true) {
                handler.post {
                    callback(Success(listOf(MovieCategory("0", listOf(Movie("1", "2"))))))
                }
            } else {
                handler.post {
                    callback(Error(RuntimeException("Exc")))
                }
            }
        }
    }
}

