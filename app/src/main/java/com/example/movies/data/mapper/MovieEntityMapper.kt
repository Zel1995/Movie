package com.example.movies.data.mapper

import com.example.movies.data.storage.entities.MovieCategoryEntity
import com.example.movies.data.storage.entities.MovieCategoryWithMovies
import com.example.movies.data.storage.entities.MovieEntity
import com.example.movies.domain.model.Movie
import com.example.movies.domain.model.MovieCategory

class MovieEntityMapper {

    private fun toMovie(movieEntity: MovieEntity): Movie {
        return Movie(
            movieEntity.adult,
            movieEntity.backdropPath,
            movieEntity.id,
            movieEntity.originalLanguage,
            movieEntity.originalTitle,
            movieEntity.overview,
            movieEntity.popularity,
            movieEntity.posterPath,
            movieEntity.releaseDate,
            movieEntity.title,
            movieEntity.video,
            movieEntity.voteAverage,
            movieEntity.voteCount,
            movieEntity.categoryName
        )
    }

    fun toMovieList(movieEntityList: List<MovieEntity>): List<Movie> {
        return movieEntityList.map { toMovie(it) }
    }

    fun toMovieCategory(movieCategoryWithMovies: MovieCategoryWithMovies): MovieCategory {
        return MovieCategory(
            movieCategoryWithMovies.movieCategoryEntity.name,
            toMovieList(movieCategoryWithMovies.result),
            movieCategoryWithMovies.movieCategoryEntity.page,
            movieCategoryWithMovies.movieCategoryEntity.totalPages
        )
    }

    fun toMovieCategoryEntity(movieCategory: MovieCategory): MovieCategoryEntity {
        return MovieCategoryEntity(movieCategory.name, movieCategory.page, movieCategory.totalPages)

    }

    private fun toMovieEntity(movie: Movie): MovieEntity {
        return MovieEntity(
            movie.adult,
            movie.backdropPath,
            movie.id,
            movie.originalLanguage,
            movie.originalTitle,
            movie.overview,
            movie.popularity,
            movie.posterPath,
            movie.releaseDate,
            movie.title,
            movie.video,
            movie.voteAverage,
            movie.voteCount,
            movie.categoryName
        )
    }

    fun toMovieEntityList(movieList: List<Movie>): List<MovieEntity> {
        return movieList.map { toMovieEntity(it) }
    }

    fun toMovieCategoryWithMovies(movieCategory: MovieCategory): MovieCategoryWithMovies {
        return MovieCategoryWithMovies(
            MovieCategoryEntity(
                movieCategory.name,
                movieCategory.page,
                movieCategory.totalPages
            ),
            movieCategory.result.map { toMovieEntity(it) }
        )
    }


}