package com.example.movies.ui.main.categories

import com.example.movies.data.repository.MovieRepositoryImpl.Companion.NOW_PLAYING_KEY
import com.example.movies.data.repository.MovieRepositoryImpl.Companion.POPULAR_KEY
import com.example.movies.data.repository.MovieRepositoryImpl.Companion.TOP_RATED_KEY
import com.example.movies.data.repository.MovieRepositoryImpl.Companion.UPCOMING_KEY

object CategoryNameMapper {

    private const val POPULAR = "Популярное"
    private const val NOW_PLAYING = "Сейчас показывают"
    private const val TOP_RATED = "Лучшие в рейтинге"
    private const val UPCOMING = "Скоро в кино"

    val map = mapOf(
        POPULAR_KEY to POPULAR,
        NOW_PLAYING_KEY to NOW_PLAYING,
        TOP_RATED_KEY to TOP_RATED,
        UPCOMING_KEY to UPCOMING
    )


}