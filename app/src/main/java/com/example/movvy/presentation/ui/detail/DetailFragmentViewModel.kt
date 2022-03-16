package com.example.movvy.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movvy.data.model.Movie
import com.example.movvy.data.model.MovieVideo
import com.example.movvy.domain.repository.MoviesRepostory
import com.example.movvy.utills.Result
import com.example.movvy.utills.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailFragmentViewModel @Inject constructor(private val moviesRepository: MoviesRepostory) :
    ViewModel() {
    val movie: StateFlow<Result<Movie>> get() = _movie
    private val _movie = MutableStateFlow<Result<Movie>>(Result.Loading())

    val movieVideos: StateFlow<Result<MovieVideo>> get() = _movieVideos
    private val _movieVideos = MutableStateFlow<Result<MovieVideo>>(Result.Loading())

    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            moviesRepository.getMovieVideos(movieId)
                .collect {
                    _movieVideos.value = it
                }
        }
    }
}