package com.example.movies.data

import android.os.Handler
import android.os.Looper
import com.example.movies.data.repository.RepositoryResult
import com.example.movies.data.repository.Success
import com.example.movies.domain.MovieRepository
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MovieCategory
import com.example.movies.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Deprecated("use MovieRepositoryImpl")
class MovieMockRepositoryImpl : MovieRepository {
    private val handler = Handler(Looper.getMainLooper())


    override suspend fun getMovies(): Flow<RepositoryResult<List<MovieCategory>>> =
        flow {
            Success(
                listOf(
                    MovieCategory(
                        "0", listOf(
                            Movie(
                                true,
                                "a",
                                1,
                                "a",
                                "a",
                                "a",
                                3f,
                                "a",
                                "a", "a",
                                true,
                                3f,
                                10,
                                )
                        )
                    )
                )
            )
        }

    override fun getMovie(id: Int): Flow<RepositoryResult<MovieDetails>> {
        TODO("Not yet implemented")
    }


}

