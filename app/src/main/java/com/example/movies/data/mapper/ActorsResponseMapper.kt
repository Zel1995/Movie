package com.example.movies.data.mapper

import com.example.movies.data.model.actors.ActorResponse
import com.example.movies.data.model.actors.ActorsResponse
import com.example.movies.domain.model.actor.Actor
import com.example.movies.domain.model.actor.Actors

class ActorsResponseMapper {
    fun toActor(actorResponse: ActorResponse): Actor {
        return Actor(
            actorResponse.adult,
            actorResponse.gender,
            actorResponse.id,
            actorResponse.knownForDepartment,
            actorResponse.name,
            actorResponse.popularity,
            actorResponse.profilePath
        )
    }

    fun toActors(actorsResponse: ActorsResponse): Actors {
        return Actors(
            actorsResponse.page,
            actorsResponse.results.map { toActor(it) },
            actorsResponse.totalPages,
            actorsResponse.totalResult,
        )
    }

}