package com.example.movies.data.repository

import com.example.movies.BuildConfig
import com.example.movies.data.mapper.ActorsResponseMapper
import com.example.movies.data.network.ActorsApi
import com.example.movies.domain.repository.ActorsRepository
import com.example.movies.domain.model.actor.Actors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ActorsRepositoryImpl(
    private val actorsApi: ActorsApi,
    private val actorsResponseMapper: ActorsResponseMapper
) : ActorsRepository {

    override fun getActors(): Flow<RepositoryResult<Actors>> = flow {
        val response = actorsApi.getActors(BuildConfig.TMDB_KEY, "ru", "1")
        val result = actorsResponseMapper.toActors(response)
        emit(Success(result))
    }

}
