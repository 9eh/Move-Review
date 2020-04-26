package com.ehcon.application.myapplication.networkData

import com.ehcon.application.myapplication.AppConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {

    private val authInterceptor = Interceptor {chain ->
        val newURL = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key",  AppConstants.tmdbApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newURL)
            .build()

        chain.proceed(newRequest)
    }

    // building http request
    private val tmdbClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(tmdbClient)
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val tmdbApi : TmdbApi = retrofit().create(TmdbApi::class.java)
}