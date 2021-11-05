package com.example.movies.data.network

import com.example.movies.data.model.details.MovieDetailsResponse
import com.example.movies.data.model.list.MoviesCategoryResponse
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

}