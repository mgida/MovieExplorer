package com.example.movieexplorer.presentation.movie_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieexplorer.data.dto.movie_details.MovieDetailsResponse
import com.example.movieexplorer.domain.mapper.UIMovieDetailsMapper
import com.example.movieexplorer.domain.use_case.GetMovieDetailsUseCase
import com.example.movieexplorer.presentation.movie_details.viewstate.MovieDetailsViewState
import com.example.movieexplorer.util.MOVIE_ID_ARG
import com.example.movieexplorer.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val uIMovieDetailsMapper: UIMovieDetailsMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailsState = MutableStateFlow(MovieDetailsViewState())
    val movieDetailsState: StateFlow<MovieDetailsViewState> = _movieDetailsState


    init {
        savedStateHandle.get<Int>(MOVIE_ID_ARG).also { movieId ->
            movieId?.let {
                getMovieDetails(movieId = it)
            }
        }
    }

    private fun getMovieDetails(movieId: Int) {
        getMovieDetailsUseCase.invoke(movieId = movieId)
            .onEach { resource: Resource<MovieDetailsResponse> ->
                when (resource) {

                    is Resource.Loading -> {
                        _movieDetailsState.value = MovieDetailsViewState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _movieDetailsState.value =
                            MovieDetailsViewState(error = resource.message.orEmpty())
                    }

                    is Resource.Success -> {

                        resource.data?.let {
                            val mappedMovie = uIMovieDetailsMapper.map(it)
                            _movieDetailsState.value = MovieDetailsViewState(data = mappedMovie)
                        }
                    }
                }

            }.launchIn(viewModelScope)
    }

}