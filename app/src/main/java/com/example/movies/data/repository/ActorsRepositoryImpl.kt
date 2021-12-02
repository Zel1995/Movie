package com.example.movies.data.repository

import com.example.movies.BuildConfig
import com.example.movies.data.mapper.ActorsResponseMapper
import com.example.movies.data.network.ActorsApi
import com.example.movies.domain.model.actor.ActorDetails
import com.example.movies.domain.model.actor.Actors
import com.example.movies.domain.repository.ActorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ActorsRepositoryImpl(
    private val actorsApi: ActorsApi,
    private val actorsResponseMapper: ActorsResponseMapper
) : ActorsRepository {

    companion object {
        const val RU_LANGUAGE_KEY = "ru"
    }

    override fun getActors(page: Int): Flow<RepositoryResult<Actors>> = flow {
        try {

        } catch (exc: Exception) {
            emit(Error<Actors>(exc))
        }
        val response = actorsApi.getActors(BuildConfig.TMDB_KEY, RU_LANGUAGE_KEY, page)
        val result = actorsResponseMapper.toActors(response)
        emit(Success(result))
    }

    override fun getActor(id: String): Flow<RepositoryResult<ActorDetails>> = flow {
        try {
            val result = actorsResponseMapper.toActorDetails(
                actorsApi.getActor(
                    id,
                    BuildConfig.TMDB_KEY,
                    RU_LANGUAGE_KEY
                )
            )
            emit(Success(result))
        } catch (exc: java.lang.Exception) {
            emit(Error<ActorDetails>(exc))
        }
    }


}
