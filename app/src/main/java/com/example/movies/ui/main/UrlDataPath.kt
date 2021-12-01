package com.example.movies.ui.main

object UrlDataPath {
    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"
    private const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="

    /** gets the poster's path. */
    fun getPosterPath(posterPath: String) = BASE_POSTER_PATH + posterPath

    /** gets the back drop's path. */
    fun getBackdropPath(backdropPath: String?) = BASE_BACKDROP_PATH + backdropPath

    /** gets the youtube video's path. */
    fun getYoutubeVideoPath(videoPath: String) = YOUTUBE_VIDEO_URL + videoPath

}