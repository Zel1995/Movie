package com.example.movies.domain

import com.example.movies.data.repository.RepositoryResult
import com.example.movies.domain.model.Actor
import com.example.movies.domain.model.Actors
import kotlinx.coroutines.flow.Flow

interface ActorsRepository {
    fun getActors(): Flow<RepositoryResult<Actors>>
}