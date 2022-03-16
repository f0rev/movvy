package com.example.movvy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movvy.api.MoviesApi
import com.example.movvy.data.model.Movie
import com.example.movvy.data.model.MovieVideo
import com.example.movvy.data.pagination.PopularMoviesPagingSource
import com.example.movvy.data.pagination.TopRatedMoviesPagingSource
import com.example.movvy.data.pagination.UpcomingMoviesPagingSource
import com.example.movvy.domain.repository.MoviesRepostory
import com.example.movvy.utills.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val DEFAULT_PAGE_SIZE = 10

class MoviesRepositoryImpl @Inject constructor(val moviesApi: MoviesApi) : MoviesRepostory {


    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = { PopularMoviesPagingSource(moviesApi) }
        ).flow
    }

    override fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = { TopRatedMoviesPagingSource(moviesApi) }
        ).flow
    }

    override fun getUpcomingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = { UpcomingMoviesPagingSource(moviesApi) }
        ).flow

    }

    override fun getMovieById(id: Int): Flow<Result<Movie>> {
        return flow {
            Result.Loading()
            try {
                val result = moviesApi.getMovieById(id)
                if (result != null)
                    Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override fun getMovieVideos(id: Int): Flow<Result<MovieVideo>> {
        return flow {
            Result.Loading()
            try {
                val result = moviesApi.getMovieVideos(id)
                if (result != null)
                    emit(Result.Success(result))
            } catch (e: Exception) {
                Result.Error(e)
            }

        }
    }


}