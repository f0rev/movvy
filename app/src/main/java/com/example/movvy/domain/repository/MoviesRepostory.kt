package com.example.movvy.domain.repository

import androidx.paging.PagingData
import com.example.movvy.data.model.Movie
import com.example.movvy.data.model.MovieVideo
import com.example.movvy.utills.Result
import kotlinx.coroutines.flow.Flow

interface MoviesRepostory {

    fun getPopularMovies(): Flow<PagingData<Movie>>

    fun getTopRatedMovies(): Flow<PagingData<Movie>>

    fun getUpcomingMovies(): Flow<PagingData<Movie>>

    fun getMovieById(id: Int):Flow<Result<Movie>>

    fun getMovieVideos(id: Int):Flow<Result<MovieVideo>>


}