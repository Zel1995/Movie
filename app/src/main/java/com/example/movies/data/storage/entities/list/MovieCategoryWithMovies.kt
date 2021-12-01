package com.example.movies.data.storage.entities.list

import androidx.room.Embedded
import androidx.room.Relation

data class MovieCategoryWithMovies(
    @Embedded
    val movieCategoryEntity: MovieCategoryEntity,
    @Relation(parentColumn = "name", entityColumn = "category_name")
    val result: List<MovieEntity>
)