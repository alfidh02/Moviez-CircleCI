package com.alfidh.moviez.di

import com.alfidh.moviez.core.domain.usecase.MovieInteractor
import com.alfidh.moviez.core.domain.usecase.MovieUseCase
import com.alfidh.moviez.detail.DetailViewModel
import com.alfidh.moviez.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
