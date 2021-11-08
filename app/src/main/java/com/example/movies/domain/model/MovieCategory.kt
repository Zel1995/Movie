package com.example.movies.domain.model

data class MovieCategory(
    val name: String,
    val result: List<Movie>,
    val page: Int = 1,
    val totalPages: Int = 1
)