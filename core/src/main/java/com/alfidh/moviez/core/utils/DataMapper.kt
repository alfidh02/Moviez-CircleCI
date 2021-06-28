package com.alfidh.moviez.core.utils

import com.alfidh.moviez.core.data.source.local.entity.MovieEntity
import com.alfidh.moviez.core.data.source.remote.response.MovieResponse
import com.alfidh.moviez.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                date = it.date,
                image = it.image,
                rate = it.rate,
                desc = it.desc,
                favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                date = it.date,
                image = it.image,
                rate = it.rate,
                desc = it.desc,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntity(input: Movie) =
        MovieEntity(
            id = input.id,
            title = input.title,
            date = input.date,
            image = input.image,
            rate = input.rate,
            desc = input.desc,
            favorite = input.favorite
        )
}