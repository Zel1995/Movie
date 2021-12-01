package com.example.movies.data.storage.entities.list

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieCategoryEntity(
    @ColumnInfo(name = "name")
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "page")
    val page: Int = 1,
    @ColumnInfo(name = "totalPages")
    val totalPages: Int = 1
)