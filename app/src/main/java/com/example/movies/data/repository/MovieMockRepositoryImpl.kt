package com.example.movies.data

import android.os.Handler
import android.os.Looper
import com.example.movies.data.repository.RepositoryResult
import com.example.movies.data.repository.Success
import com.example.movies.domain.model.movie.Movie
import com.example.movies.domain.model.movie.MovieCategory
import com.example.movies.domain.model.movie.MovieDetails
import com.example.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Deprecated("use MovieRepositoryImpl")
class MovieMockRepositoryImpl : MovieRepository {
    private val handler = Handler(Looper.getMainLooper())


    override fun getMovies(): Flow<RepositoryResult<List<MovieCategory>>> =
        flow {
            Success(
                listOf(
                    MovieCategory(
                        "0", listOf(
                            Movie(
                                true,
                                "path",
                                1,
                                "ru",
                                "Name",
                                "some text",
                                3f,
                                "path",
                                "release",
                                "title",
                                true,
                                3f,
                                10,
                                "category"
                            )
                        )
                    )
                )
            )
        }

    override fun getMovie(id: Int): Flow<RepositoryResult<MovieDetails>> {
        TODO("Not yet implemented")
    }

    override fun searchMovies(query: String): Flow<RepositoryResult<MovieCategory>> {
        TODO("Not yet implemented")
    }


}

