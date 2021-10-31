package com.example.movies.data.model

import com.google.gson.annotations.SerializedName

data class TmdbResponse(
    @SerializedName("results")
    val result: List<MovieResponse>
)