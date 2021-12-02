package com.example.movies.domain.repository

import com.example.movies.data.repository.RepositoryResult
import com.example.movies.domain.model.actor.ActorDetails
import com.example.movies.domain.model.actor.Actors
import kotlinx.coroutines.flow.Flow

interface ActorsRepository {
    fun getActors(page: Int): Flow<RepositoryResult<Actors>>
    fun getActor(id: String): Flow<RepositoryResult<ActorDetails>>
}