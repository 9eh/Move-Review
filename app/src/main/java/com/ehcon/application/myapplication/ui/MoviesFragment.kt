package com.ehcon.application.myapplication.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.ehcon.application.myapplication.R
import com.ehcon.application.myapplication.viewmodel.MoviesViewModel
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class MoviesFragment : Fragment() {

    companion object {
        const val TAG = "MoviesFragment"
    }

    //    private val viewModel: MoviesViewModel by viewModel()
    private val viewModel by inject<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        viewModel.fetchMovies()

        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner, Observer { movies ->
            movies.forEach { movie ->
                Log.d(TAG, "movie: ${movie.title}")
            }
        })

    }
}
