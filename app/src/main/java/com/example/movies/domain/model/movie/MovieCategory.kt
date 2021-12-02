package com.example.movies.domain.model.movie

data class MovieCategory(
    val name: String,
    val results: List<Movie>,
    val page: Int = 1,
    val totalPages: Int = 1
)