package com.example.movvy.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movvy.api.MoviesApi
import com.example.movvy.data.model.Movie
import retrofit2.HttpException
import java.io.IOException

const val DEFAULT_PAGE_INDEX = 1
class PopularMoviesPagingSource (val godsApi: MoviesApi):PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = godsApi.getPopularMovies(page,params.loadSize)
            LoadResult.Page(
                response.results, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        }
        catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }

}