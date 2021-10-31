package com.example.movies.data.network

import com.example.movies.BuildConfig
import com.example.movies.data.model.TmdbResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("3/movie/{category}")
    fun getMovies(
        @Path("category") category: String,
        @Query("api_key")key:String,
        @Query("language")language:String)
    :Call<TmdbResponse>

}