package com.berkayak.mymovies.data.network

import com.berkayak.mymovies.data.model.MovieListResponse
import com.berkayak.mymovies.utilities.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String = Const.API_KEY,
        @Query("language") language: String = Const.API_LANG,
        @Query("page") page: Int = 1
    ): Response<MovieListResponse>
}