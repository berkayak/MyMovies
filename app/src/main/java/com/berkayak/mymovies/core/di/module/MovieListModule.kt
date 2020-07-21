package com.berkayak.mymovies.core.di.module

import com.berkayak.mymovies.core.di.scope.MovieListScope
import com.berkayak.mymovies.data.repository.MovieListRepository
import com.berkayak.mymovies.data.network.MovieService
import com.berkayak.mymovies.viewmodel.factory.MovieListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieListModule {

    @Provides
    @MovieListScope
    fun movieListRepository(service: MovieService) =
        MovieListRepository(service)

    @Provides
    @MovieListScope
    fun movieListViewModelFactory(repository: MovieListRepository) =
        MovieListViewModelFactory(repository)
}