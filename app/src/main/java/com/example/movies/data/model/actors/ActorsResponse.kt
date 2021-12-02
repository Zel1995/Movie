package com.example.movies.data.model.actors

import com.google.gson.annotations.SerializedName

data class ActorsResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ActorResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_result")
    val totalResult: Int
)
