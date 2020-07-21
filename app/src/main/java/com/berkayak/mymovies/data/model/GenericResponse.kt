package com.berkayak.mymovies.data.model

sealed class GenericResponse<out T: Any> {
    data class Success<out T : Any>(val result: T?) : GenericResponse<T>()
    data class Failure(val message: String?) : GenericResponse<Nothing>()
    data class Error(val exception: Exception) : GenericResponse<Nothing>()
}