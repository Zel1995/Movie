package com.example.movies.data.model.videos

import com.example.movies.domain.model.video.VideoResult
import com.example.movies.domain.model.video.Videos
import com.google.gson.annotations.SerializedName

data class VideosResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val results:List<VideoResultResponse>
        )