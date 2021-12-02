package com.example.movies.data.mapper

import com.example.movies.data.model.details.MovieDetailsResponse
import com.example.movies.data.model.details.ProductionCompaniesResponse
import com.example.movies.data.model.list.MovieResponse
import com.example.movies.data.model.list.MoviesCategoryResponse
import com.example.movies.domain.model.movie.Movie
import com.example.movies.domain.model.movie.MovieCategory
import com.example.movies.domain.model.movie.MovieDetails
import com.example.movies.domain.model.movie.ProductionCompanies
import javax.inject.Inject

class MovieApiResponseMapper @Inject constructor() {

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
            movieDetailsResponse.productionCompanies.map { toProductionCompanies(it) },
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

    fun toProductionCompanies(productionCompaniesResponse: ProductionCompaniesResponse): ProductionCompanies {
        return productionCompaniesResponse.let {
            ProductionCompanies(
                it.id,
                it.logoPath,
                it.name,
                it.originCountry,
            )
        }

    }


    private fun toMovie(movieResponse: MovieResponse, categoryName: String): Movie {
        return Movie(
            movieResponse.adult,
            movieResponse.backdropPath,
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
            movieResponse.voteCount,
            categoryName
        )
    }

    private fun toMovieList(
        movieResponseList: List<MovieResponse>,
        categoryName: String = ""
    ): List<Movie> {
        return movieResponseList.map { toMovie(it, categoryName) }
    }

    fun toMovieCategory(
        moviesCategoryResponse: MoviesCategoryResponse,
        categoryName: String = ""
    ): MovieCategory {
        return MovieCategory(
            categoryName,
            toMovieList(moviesCategoryResponse.results, categoryName),
            moviesCategoryResponse.page,
            moviesCategoryResponse.totalPages
        )
    }

}