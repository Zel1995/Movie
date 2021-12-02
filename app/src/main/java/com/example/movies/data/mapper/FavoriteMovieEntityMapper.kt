package com.example.movies.data.mapper

import com.example.movies.data.storage.entities.list.FavoriteMovieEntity
import com.example.movies.domain.model.movie.Movie
import javax.inject.Inject

class FavoriteMovieEntityMapper @Inject constructor() {
    fun toMovie(favoriteMovieEntity: FavoriteMovieEntity): Movie {
        return Movie(
            favoriteMovieEntity.adult,
            favoriteMovieEntity.backdropPath,
            favoriteMovieEntity.id,
            favoriteMovieEntity.originalLanguage,
            favoriteMovieEntity.originalTitle,
            favoriteMovieEntity.overview,
            favoriteMovieEntity.popularity,
            favoriteMovieEntity.posterPath,
            favoriteMovieEntity.releaseDate,
            favoriteMovieEntity.title,
            favoriteMovieEntity.video,
            favoriteMovieEntity.voteAverage,
            favoriteMovieEntity.voteCount,
            favoriteMovieEntity.categoryName
        )
    }

    fun toNullableMovie(favoriteMovieEntity: FavoriteMovieEntity?): Movie? {
        return favoriteMovieEntity?.let {
            Movie(
                it.adult,
                it.backdropPath,
                it.id,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.releaseDate,
                it.title,
                it.video,
                it.voteAverage,
                it.voteCount,
                it.categoryName
            )
        }
    }

    fun toMovieList(favoriteMovieEntityList: List<FavoriteMovieEntity>): List<Movie> {
        return favoriteMovieEntityList.map { toMovie(it) }
    }

    fun toFavoriteMovie(movie: Movie): FavoriteMovieEntity {
        return FavoriteMovieEntity(
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
}