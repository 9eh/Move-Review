package com.ehcon.application.myapplication

import com.ehcon.application.myapplication.networkData.ApiFactory
import com.ehcon.application.myapplication.repository.MovieRepository
import com.ehcon.application.myapplication.viewmodel.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module



val movieRepoModule = module {
    factory { ApiFactory.tmdbApi }
    single { MovieRepository(get()) }
}

val moviesViewModelModule = module {
    viewModel { MoviesViewModel(get()) }
}