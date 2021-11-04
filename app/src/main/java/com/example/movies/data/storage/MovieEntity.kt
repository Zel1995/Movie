package com.example.movies.data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val category:String,
    val name: String
)