package com.berkayak.mymovies.data.repository

import com.berkayak.mymovies.data.model.GenericResponse
import com.berkayak.mymovies.data.model.MovieListResponse
import com.berkayak.mymovies.data.network.MovieService
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getMovieList(page: Int): GenericResponse<MovieListResponse> {
        return try {
            val response = movieService.getMovieList(page = page)
            if (response.isSuccessful) {
                GenericResponse.Success<MovieListResponse>(response.body())
            } else {
                GenericResponse.Failure(response.body().toString())
            }
        } catch (e: Exception) {
            GenericResponse.Error(e)
        }
    }
}
