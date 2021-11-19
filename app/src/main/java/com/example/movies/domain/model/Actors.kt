package com.example.movies.domain.model

data class Actors(
    val page: Int,
    val results: List<Actor>,
    val totalPages: Int,
    val totalResult: Int
)