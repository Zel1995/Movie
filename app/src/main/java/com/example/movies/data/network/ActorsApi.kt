package com.example.movies.data.network

import com.example.movies.data.model.actors.ActorsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ActorsApi {
    @GET("3/person/popular")
    suspend fun getActors(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): ActorsResponse
}
