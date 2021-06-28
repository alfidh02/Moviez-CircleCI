package com.alfidh.moviez.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alfidh.moviez.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}