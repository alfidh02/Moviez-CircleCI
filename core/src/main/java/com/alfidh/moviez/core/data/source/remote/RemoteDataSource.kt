package com.alfidh.moviez.core.data.source.remote

import com.alfidh.moviez.core.data.source.remote.network.ApiResponse
import com.alfidh.moviez.core.data.source.remote.network.ApiService
import com.alfidh.moviez.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getTopMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results) )
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}