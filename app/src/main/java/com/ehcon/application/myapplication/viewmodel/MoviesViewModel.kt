package com.ehcon.application.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehcon.application.myapplication.repository.MovieRepository
import com.ehcon.application.myapplication.model.TmdbMovie
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesViewModel (private val repository: MovieRepository)
    : ViewModel() {
    private val TAG = "MoviesViewModel"

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    //private val repository: MovieRepository = MovieRepository()

    private val _popularMoviesLiveData = MutableLiveData<MutableList<TmdbMovie>>()
    val popularMoviesLiveData: MutableLiveData<MutableList<TmdbMovie>>
        get() = _popularMoviesLiveData

    fun fetchMovies() {
        scope.launch {
            val popularMovies = repository.getPopularMovies()
            _popularMoviesLiveData.postValue(popularMovies)
        }
    }

    private fun cancelAllRequests() = coroutineContext.cancel()


    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
        Log.d(TAG, "onCleared() called")
    }


}
