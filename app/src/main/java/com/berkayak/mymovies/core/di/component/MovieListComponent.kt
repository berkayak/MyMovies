package com.berkayak.mymovies.core.di.component

import com.berkayak.mymovies.core.di.module.MovieListModule
import com.berkayak.mymovies.core.di.scope.MovieListScope
import com.berkayak.mymovies.data.network.MovieService
import com.berkayak.mymovies.ui.MainActivity
import com.berkayak.mymovies.ui.fragment.MovieListFragment
import com.berkayak.mymovies.viewmodel.factory.MovieListViewModelFactory
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [MovieListModule::class])
@MovieListScope
interface MovieListComponent {

    fun viewModelFactory(): MovieListViewModelFactory
    fun service(): MovieService

    @Subcomponent.Builder
    interface Builder {
        fun module(movieListModule: MovieListModule): Builder
        fun create(): MovieListComponent
    }

    fun inject(fragment: MovieListFragment)
}
