package com.example.movies.data.network

import android.net.Uri
import com.example.movies.data.model.details.MovieDetailsResponse
import com.example.movies.data.model.list.MoviesCategoryResponse
import com.example.movies.data.model.videos.VideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("3/movie/{category}")
    suspend fun getMovies(
        @Path("category") category: String,
        @Query("api_key") key: String,
        @Query("language") language: String
    ): MoviesCategoryResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") language: String
    ): MovieDetailsResponse

    @GET("3/search/movie")
    suspend fun searchMovies(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("query") query: Uri,
    ): MoviesCategoryResponse

    @GET("3/movie/{id}/videos")
    suspend fun getVideo(
        @Path("id")id:Int,
        @Query("api_key")key:String,
        @Query("language")language: String):VideosResponse
}