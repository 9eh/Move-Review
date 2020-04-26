package com.ehcon.application.myapplication.repository

import com.ehcon.application.myapplication.networkData.TmdbApi
import com.ehcon.application.myapplication.model.TmdbMovie

class MovieRepository(private val apiWebservice: TmdbApi): BaseRepository() {

//    private val apiWebservice =  ApiFactory.tmdbApi

    suspend fun getPopularMovies() : MutableList<TmdbMovie>? {

        val movieResponse = safeApiCall(
            call = {apiWebservice.getPopularMovies().await()},
            errorMessage = "Error Fetching Popular Movies"
        )

        return movieResponse?.results?.toMutableList()
    }
}