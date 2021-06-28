package com.alfidh.moviez.core.data.source.remote.network

import com.alfidh.moviez.BuildConfig
import com.alfidh.moviez.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    companion object {
        const val API_TOKEN = BuildConfig.API_TOKEN
    }

    @GET("movie/top_rated?api_key=$API_TOKEN")
    suspend fun getTopMovies() : ListMovieResponse
}