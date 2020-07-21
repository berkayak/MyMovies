package com.berkayak.mymovies.data.model


import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<Movie>
)