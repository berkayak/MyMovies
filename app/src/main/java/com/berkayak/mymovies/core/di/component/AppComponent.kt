package com.berkayak.mymovies.core.di.component

import android.content.Context
import com.berkayak.mymovies.core.di.module.AppModule
import com.berkayak.mymovies.core.di.module.MovieListModule
import com.berkayak.mymovies.core.di.module.NetworkModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

    fun context(): Context

    fun retrofit(): Retrofit

    fun movieListComponent(): MovieListComponent.Builder
}

//https://medium.com/mobile-app-development-publication/dagger-2-subcomponent-illustrated-kotlin-3a0c78738f69