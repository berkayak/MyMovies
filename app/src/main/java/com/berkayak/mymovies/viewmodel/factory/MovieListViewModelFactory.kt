package com.berkayak.mymovies.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.berkayak.mymovies.data.repository.MovieListRepository
import com.berkayak.mymovies.viewmodel.MovieListViewModel
import javax.inject.Inject

class MovieListViewModelFactory @Inject constructor(
    private val repository: MovieListRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(repository) as T
    }
}