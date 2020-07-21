package com.berkayak.mymovies.core

import android.app.Application
import com.berkayak.mymovies.core.di.component.AppComponent
import com.berkayak.mymovies.core.di.component.DaggerAppComponent
import com.berkayak.mymovies.core.di.module.AppModule
import com.berkayak.mymovies.core.di.module.NetworkModule

class MovieApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule())
            .build()
    }
}