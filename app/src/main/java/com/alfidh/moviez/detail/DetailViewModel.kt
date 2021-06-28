package com.alfidh.moviez.detail

import androidx.lifecycle.ViewModel
import com.alfidh.moviez.core.domain.model.Movie
import com.alfidh.moviez.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newState)
}