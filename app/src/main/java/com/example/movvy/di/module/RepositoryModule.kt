package com.example.movvy.di.module

import com.example.movvy.data.repository.MoviesRepositoryImpl
import com.example.movvy.domain.repository.MoviesRepostory
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl):MoviesRepostory
}