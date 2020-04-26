package com.ehcon.application.myapplication.networkData

class MovieRepository(private val api: TmdbApi): BaseRepository() {

    suspend fun getPopularMovies() : MutableList<TmdbMovie>? {

        val movieResponse = safeApiCall(
            call = {api.getPopularMovies().await()},
            errorMessage = "Error Fetching Popular Movies"
        )

        return movieResponse?.results?.toMutableList()
    }
}