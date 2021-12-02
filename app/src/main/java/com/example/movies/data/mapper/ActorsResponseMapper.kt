package com.example.movies.data.mapper

import com.example.movies.data.model.actors.ActorDetailsResponse
import com.example.movies.data.model.actors.ActorResponse
import com.example.movies.data.model.actors.ActorsResponse
import com.example.movies.domain.model.actor.Actor
import com.example.movies.domain.model.actor.ActorDetails
import com.example.movies.domain.model.actor.Actors
import javax.inject.Inject

class ActorsResponseMapper @Inject constructor() {
    private fun toActor(actorResponse: ActorResponse): Actor {
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

    fun toActorDetails(actorDetailsResponse: ActorDetailsResponse): ActorDetails {
        return ActorDetails(
            actorDetailsResponse.adult,
            actorDetailsResponse.alsoKnownAs,
            actorDetailsResponse.biography,
            actorDetailsResponse.birthday,
            actorDetailsResponse.deathDay,
            actorDetailsResponse.gender,
            actorDetailsResponse.homepage,
            actorDetailsResponse.id,
            actorDetailsResponse.imdbId,
            actorDetailsResponse.knownForDepartment,
            actorDetailsResponse.name,
            actorDetailsResponse.placeOfBirth,
            actorDetailsResponse.popularity,
            actorDetailsResponse.profilePath,
        )
    }


}