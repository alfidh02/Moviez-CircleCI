package com.alfidh.moviez.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alfidh.moviez.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}