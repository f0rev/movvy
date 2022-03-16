package com.example.movvy.presentation.ui.mainlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movvy.data.model.Movie
import com.example.movvy.domain.repository.MoviesRepostory
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainListViewModel @Inject constructor(moviesRepository: MoviesRepostory) : ViewModel() {
    val popularMovies: StateFlow<PagingData<Movie>> get() = _popularMovies
    private val _popularMovies = MutableStateFlow(PagingData.empty<Movie>())

    val topRatedMovies: StateFlow<PagingData<Movie>> get() = _topRatedMovies
    private val _topRatedMovies = MutableStateFlow(PagingData.empty<Movie>())

    val upcomingMovies: StateFlow<PagingData<Movie>> get() = _upcomingMovies
    private val _upcomingMovies = MutableStateFlow(PagingData.empty<Movie>())


    init {
        viewModelScope.launch {
            combine(
                listOf(
                    moviesRepository.getTopRatedMovies().cachedIn(viewModelScope),
                    moviesRepository.getPopularMovies().cachedIn(viewModelScope),
                    moviesRepository.getUpcomingMovies().cachedIn(viewModelScope)
                )
            ) {
                it
            }.collect {
                _popularMovies.value = it[0]
                _topRatedMovies.value = it[1]
                _upcomingMovies.value = it[2]
            }

        }
    }

}