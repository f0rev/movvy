package com.example.movvy.di.module

import android.content.Context
import com.example.core_network.core_network_impl.di.CoreNetworkComponent
import com.example.movvy.App
import com.example.movvy.api.MoviesApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(): Context {
        return App.appContext
    }

    @Provides
    @Singleton
    fun dogsApiService(): MoviesApi {
        return CoreNetworkComponent.get().getRetrofit().create(MoviesApi::class.java)
    }
}


