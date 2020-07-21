package com.berkayak.mymovies.viewmodel

import androidx.lifecycle.*
import com.berkayak.mymovies.data.model.GenericResponse
import com.berkayak.mymovies.data.model.MovieListResponse
import com.berkayak.mymovies.data.repository.MovieListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val repository: MovieListRepository): ViewModel() {

    val movieList = MutableLiveData<GenericResponse<MovieListResponse>>()
//    val liveData = movieList.switchMap { movieList }

    fun getMovieList(page: Int): LiveData<GenericResponse<MovieListResponse>> {
        viewModelScope.launch {
            movieList.value = repository.getMovieList(page)
        }
        return movieList
    }
}