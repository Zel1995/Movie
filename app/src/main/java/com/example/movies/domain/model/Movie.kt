package com.example.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(val id:String,
                 val name:String):Parcelable