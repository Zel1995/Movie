package com.example.movies.data.mapper

import com.example.movies.data.storage.entities.FavoriteMovieEntity
import com.example.movies.domain.model.Movie

class FavoriteMovieEntityMapper {
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
    }

    fun toMovieList(favoriteMovieEntityList: List<FavoriteMovieEntity>): List<Movie> {
        return favoriteMovieEntityList.map { toMovie(it) }
    }

    fun toFavoriteMovie(movie:Movie):FavoriteMovieEntity{
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