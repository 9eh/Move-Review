package com.ehcon.application.myapplication.networkData

import com.ehcon.application.myapplication.model.TmdbMovie
import com.ehcon.application.myapplication.model.TmdbMovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApi {

    @GET("movie/popular")
    fun getPopularMovies(): Deferred<Response<TmdbMovieResponse>>

    @GET("movie/{id}")
    fun getMovieById(@Path("id") id: Int) : Deferred<Response<TmdbMovie>>

}