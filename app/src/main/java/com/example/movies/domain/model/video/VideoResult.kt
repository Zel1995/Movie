package com.example.movies.domain.model.video

data class VideoResult (
    val name: String,
    val key: String,
    val site: String,
    val size: Long,
    val type: String,
    val official: Boolean,
    val publishedAt: String?,
    val id: String
        )