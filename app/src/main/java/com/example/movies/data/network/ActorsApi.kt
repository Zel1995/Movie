package com.example.movies.data.network

import com.example.movies.data.model.actors.ActorDetailsResponse
import com.example.movies.data.model.actors.ActorsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ActorsApi {
    @GET("3/person/popular")
    suspend fun getActors(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): ActorsResponse

    @GET("3/person/{actor_id}")
    suspend fun getActor(
        @Path("actor_id") actorId: String,
        @Query("api_key") key: String,
        @Query("language") language: String
    ): ActorDetailsResponse
}
//https://api.themoviedb.org/3/person/popular?api_key=6267054eeb5e792857b08b1a08490bcc&language=ru&page=1
//https://api.themoviedb.org/3/person/1136406?api_key=6267054eeb5e792857b08b1a08490bcc&language=ru