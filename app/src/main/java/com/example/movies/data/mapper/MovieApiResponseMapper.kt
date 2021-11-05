package com.example.movies.data.mapper

import com.example.movies.data.model.details.MovieDetailsResponse
import com.example.movies.data.model.list.MovieResponse
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MovieDetails

class MovieApiResponseMapper {

    fun toMovieDetails(movieDetailsResponse: MovieDetailsResponse): MovieDetails {
        return MovieDetails(
            movieDetailsResponse.adult,
            movieDetailsResponse.backdropPath,
            movieDetailsResponse.budget,
            movieDetailsResponse.genres.map { (it.name) },
            movieDetailsResponse.id,
            movieDetailsResponse.imdbId,
            movieDetailsResponse.originalLanguage,
            movieDetailsResponse.originalTitle,
            movieDetailsResponse.overview,
            movieDetailsResponse.popularity,
            movieDetailsResponse.posterPath,
            movieDetailsResponse.productionCompanies.map { it.name },
            movieDetailsResponse.releaseDate,
            movieDetailsResponse.revenue,
            movieDetailsResponse.runTime,
            movieDetailsResponse.status,
            movieDetailsResponse.tagline,
            movieDetailsResponse.title,
            movieDetailsResponse.video,
            movieDetailsResponse.voteAverage,
            movieDetailsResponse.voteCount
        )
    }
    fun toMovieDetailsList(movieDetailsResponse: List<MovieDetailsResponse>):List<MovieDetails>{
        return movieDetailsResponse.map { toMovieDetails(it) }
    }

    fun toMovie(movieResponse: MovieResponse): Movie {
        return Movie(
            movieResponse.adult,
            movieResponse.backdropPath,
            movieResponse.genreIds,
            movieResponse.id,
            movieResponse.originalLanguage,
            movieResponse.originalTitle,
            movieResponse.overview,
            movieResponse.popularity,
            movieResponse.posterPath,
            movieResponse.releaseDate,
            movieResponse.title,
            movieResponse.video,
            movieResponse.voteAverage,
            movieResponse.voteCount
        )
    }
    fun toMovieList(movieResponseList:List<MovieResponse>):List<Movie>{
        return movieResponseList.map { toMovie(it) }
    }


}