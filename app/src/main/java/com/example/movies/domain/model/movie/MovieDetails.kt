package com.example.movies.domain.model.movie

data class MovieDetails(
    val adult: Boolean,
    val backdropPath: String?,
    val budget: Long,
    val genres: List<String>,
    val id: Int,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: String,
    val posterPath: String?,
    val productionCompanies: List<String>,
    val releaseDate: String,
    val revenue: Long,
    val runTime: Int?,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: Float,
    val voteCount: Int
)