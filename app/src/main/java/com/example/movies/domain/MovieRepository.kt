package com.example.movies.domain

import com.example.movies.data.RepositoryResult
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MovieCategory
import java.util.concurrent.Executor

interface MovieRepository {
    fun getMovies(executor: Executor, callback: (result: RepositoryResult<List<MovieCategory>>) -> Unit)
}