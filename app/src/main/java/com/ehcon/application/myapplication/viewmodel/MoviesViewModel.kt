package com.ehcon.application.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehcon.application.myapplication.networkData.ApiFactory
import com.ehcon.application.myapplication.networkData.MovieRepository
import com.ehcon.application.myapplication.networkData.TmdbMovie
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesViewModel : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: MovieRepository = MovieRepository(ApiFactory.tmdbApi)

    private val _popularMoviesLiveData = MutableLiveData<MutableList<TmdbMovie>>()
    val popularMoviesLiveData: MutableLiveData<MutableList<TmdbMovie>>
        get() = _popularMoviesLiveData

    fun fetchMovies() {
        scope.launch {
            val popularMovies = repository.getPopularMovies()
            _popularMoviesLiveData.postValue(popularMovies)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()


    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }


}
