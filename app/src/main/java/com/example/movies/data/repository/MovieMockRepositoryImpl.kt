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
@Deprecated("use MovieRepositoryImpl")
class MovieMockRepositoryImpl : MovieRepository {
    private val handler = Handler(Looper.getMainLooper())


    override suspend fun getMovies(): RepositoryResult<List<MovieCategory>> {
        return Success(listOf(MovieCategory("0", listOf(Movie(1, "2")))))
    }


}

