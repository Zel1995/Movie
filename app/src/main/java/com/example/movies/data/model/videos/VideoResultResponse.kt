package com.example.movies.data.model.videos

import com.google.gson.annotations.SerializedName

data class VideoResultResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Long,
    @SerializedName("type")
    val type: String,
    @SerializedName("official")
    val official: Boolean,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("id")
    val id: String
)