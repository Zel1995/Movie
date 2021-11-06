package com.example.movies.data.mapper

import com.example.movies.data.storage.MovieEntity
import com.example.movies.domain.model.Movie

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
            movieEntity.voteCount
        )
    }

    fun toMovieList(movieEntityList: List<MovieEntity>?): List<Movie>? {
        return movieEntityList?.map { toMovie(it) }
    }

    private fun toMovieEntity(movie: Movie, category: String): MovieEntity {
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
            category
        )
    }

    fun toMovieEntityList(movieList: List<Movie>, category: String): List<MovieEntity> {
        return movieList.map { toMovieEntity(it, category) }
    }
}