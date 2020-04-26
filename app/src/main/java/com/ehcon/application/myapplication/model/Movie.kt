package com.ehcon.application.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TmdbMovie(
    @PrimaryKey val id: Int,
    val vote_average: Double,
    val title: String,
    val overview: String,
    val adult: Boolean
)

// Data Model for the Response returned from the TMDB Api
data class TmdbMovieResponse(
    val results: List<TmdbMovie>
)