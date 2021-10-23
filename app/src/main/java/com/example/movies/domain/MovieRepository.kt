package com.example.movies.domain

import com.example.movies.data.RepositoryResult

interface MovieRepository {
    fun getMovie(callback:(result:RepositoryResult<Movie>)->Unit)
    fun shutDown()
}