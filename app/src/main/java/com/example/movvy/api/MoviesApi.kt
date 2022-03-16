package com.example.movvy.api

import com.example.movvy.data.model.Movie
import com.example.movvy.data.model.MovieVideo
import com.example.movvy.data.model.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int, @Query("limit") size: Int): Result

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int, @Query("limit") size: Int): Result

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int, @Query("limit") size: Int): Result

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") movieId: Int): MovieVideo

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId: Int): MovieVideo

    @GET("v1/favourites")
    suspend fun saveFavorite(@Query("page") page: Int, @Query("limit") size: Int): Result

    companion object {
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }


}