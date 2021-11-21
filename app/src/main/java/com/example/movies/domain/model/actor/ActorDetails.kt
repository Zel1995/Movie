package com.example.movies.domain.model.actor

data class ActorDetails(
    val adult:Boolean,
    val alsoKnownAs:List<String>,
    val biography:String,
    val birthday:String,
    val deathDay:String?,
    val gender:Int,
    val homepage:String?,
    val id:Int,
    val imdbId:String,
    val knownForDepartment:String,
    val name:String,
    val placeOfBirth:String,
    val popularity:Float,
    val profilePath:String
)