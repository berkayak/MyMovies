package com.berkayak.mymovies.core.di.module

import android.content.Context
import com.berkayak.mymovies.core.di.component.MovieListComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MovieListComponent::class])
class AppModule constructor(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext() = context
}