package com.example.movies.data.mapper

import com.example.movies.data.model.videos.VideoResultResponse
import com.example.movies.data.model.videos.VideosResponse
import com.example.movies.domain.model.video.VideoResult
import com.example.movies.domain.model.video.Videos
import javax.inject.Inject

class VideosResponseMapper @Inject constructor() {
    private fun toVideoResult(videoResultResponse: VideoResultResponse): VideoResult {
        return VideoResult(
            videoResultResponse.name,
            videoResultResponse.key,
            videoResultResponse.site,
            videoResultResponse.size,
            videoResultResponse.type,
            videoResultResponse.official,
            videoResultResponse.publishedAt,
            videoResultResponse.id,
        )
    }

    fun toVideos(videosResponse: VideosResponse): Videos {
        return Videos(
            videosResponse.id,
            videosResponse.results.map { toVideoResult(it) }
        )
    }
}