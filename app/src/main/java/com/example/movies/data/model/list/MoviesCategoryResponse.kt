package com.example.movies.data.model.list

import com.google.gson.annotations.SerializedName

data class MoviesCategoryResponse(
    @SerializedName("results")
    val result: List<MovieResponse>,
    @SerializedName("page")
    val page:Int,
    @SerializedName("total_pages")
    val totalPages:Int
)