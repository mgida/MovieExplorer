package com.example.movieexplorer.presentation.movie_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieexplorer.data.dto.movie_details.MovieDetailsResponse
import com.example.movieexplorer.data.dto.similar_movies.SimilarMoviesResponse
import com.example.movieexplorer.domain.mapper.UIMovieDetailsMapper
import com.example.movieexplorer.domain.mapper.UISimilarMoviesMapper
import com.example.movieexplorer.domain.use_case.GetMovieDetailsUseCase
import com.example.movieexplorer.domain.use_case.GetSimilarMoviesUseCase
import com.example.movieexplorer.presentation.movie_details.event.MovieDetailsEvent
import com.example.movieexplorer.presentation.movie_details.viewstate.MovieDetailsViewState
import com.example.movieexplorer.presentation.movie_details.viewstate.SimilarMoviesViewState
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
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val uiSimilarMoviesMapper: UISimilarMoviesMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailsState = MutableStateFlow(MovieDetailsViewState())
    val movieDetailsState: StateFlow<MovieDetailsViewState> = _movieDetailsState


    private val _similarMoviesViewState = MutableStateFlow(SimilarMoviesViewState())
    val similarMoviesViewState: StateFlow<SimilarMoviesViewState> = _similarMoviesViewState


    init {
        savedStateHandle.get<Int>(MOVIE_ID_ARG).also { movieId ->
            movieId?.let {
                getMovieDetails(movieId = it)
                getSimilarMovies(movieId = it)
            }
        }
    }


    fun onEvent(movieDetailsEvent: MovieDetailsEvent) {
        when (movieDetailsEvent) {
            is MovieDetailsEvent.GetMoviesDetails -> getMovieDetails(movieDetailsEvent.movieId)
            is MovieDetailsEvent.GetSimilarMovies -> getSimilarMovies(movieDetailsEvent.movieId)
        }
    }

    private fun getSimilarMovies(movieId: Int) {
        getSimilarMoviesUseCase.invoke(movieId = movieId)
            .onEach { resource: Resource<SimilarMoviesResponse> ->
                when (resource) {

                    is Resource.Loading -> {
                        _similarMoviesViewState.value = SimilarMoviesViewState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _similarMoviesViewState.value =
                            SimilarMoviesViewState(error = resource.message.orEmpty())
                    }

                    is Resource.Success -> {

                        resource.data?.results?.let {
                            val mappedMovie = uiSimilarMoviesMapper.map(it)
                            _similarMoviesViewState.value =
                                SimilarMoviesViewState(data = mappedMovie)
                        }
                    }
                }

            }.launchIn(viewModelScope)
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