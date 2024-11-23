package com.example.movieexplorer.presentation.movie_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieexplorer.data.dto.credits.MovieCreditsResponse
import com.example.movieexplorer.data.dto.movie_details.MovieDetailsResponse
import com.example.movieexplorer.data.dto.similar_movies.SimilarMoviesResponse
import com.example.movieexplorer.domain.mapper.UIMovieDetailsMapper
import com.example.movieexplorer.domain.mapper.UISimilarMoviesMapper
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import com.example.movieexplorer.domain.use_case.DeleteMovieUseCase
import com.example.movieexplorer.domain.use_case.GetMovieCreditsUseCase
import com.example.movieexplorer.domain.use_case.GetMovieDetailsUseCase
import com.example.movieexplorer.domain.use_case.GetSimilarMoviesUseCase
import com.example.movieexplorer.domain.use_case.ProcessCreditsUseCase
import com.example.movieexplorer.domain.use_case.SaveMovieUseCase
import com.example.movieexplorer.presentation.movie_details.event.MovieDetailsEvent
import com.example.movieexplorer.presentation.movie_details.viewstate.CreditsViewState
import com.example.movieexplorer.presentation.movie_details.viewstate.MovieDetailsViewState
import com.example.movieexplorer.presentation.movie_details.viewstate.SimilarMoviesViewState
import com.example.movieexplorer.util.MOVIE_ID_ARG
import com.example.movieexplorer.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val uIMovieDetailsMapper: UIMovieDetailsMapper,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val uiSimilarMoviesMapper: UISimilarMoviesMapper,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    private val creditsUseCase: ProcessCreditsUseCase,

    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailsState = MutableStateFlow(MovieDetailsViewState())
    val movieDetailsState: StateFlow<MovieDetailsViewState> = _movieDetailsState


    private val _similarMoviesViewState = MutableStateFlow(SimilarMoviesViewState())
    val similarMoviesViewState: StateFlow<SimilarMoviesViewState> = _similarMoviesViewState

    private val _creditsViewState = MutableStateFlow(CreditsViewState())
    val creditsViewState: StateFlow<CreditsViewState> = _creditsViewState

    private val _isInWatchlist = MutableStateFlow(false)
    val isInWatchlist: StateFlow<Boolean> = _isInWatchlist

    init {
        savedStateHandle.get<Int>(MOVIE_ID_ARG).also { movieId ->
            movieId?.let {
                getMovieDetails(movieId = it)
                getSimilarMovies(movieId = it)
                getMovieCredits(movieId)
            }
        }
    }

    private fun getMovieCredits(movieId: Int) {
        getMovieCreditsUseCase.invoke(movieId = movieId)
            .onEach { resource: Resource<MovieCreditsResponse> ->
                when (resource) {

                    is Resource.Loading -> {
                        _creditsViewState.value = CreditsViewState(isLoading = true)
                    }

                    is Resource.Error -> {

                        _creditsViewState.value =
                            CreditsViewState(error = resource.message.orEmpty())
                    }

                    is Resource.Success -> {
                        resource.data?.let { movieCreditsResponse ->

                            val (actors, directors) = creditsUseCase.invoke(movieCreditsResponse)
                            _creditsViewState.value = CreditsViewState(
                                actors = actors ?: listOf(),
                                directors = directors ?: listOf()
                            )
                        }
                    }
                }

            }.launchIn(viewModelScope)
    }


    fun onEvent(movieDetailsEvent: MovieDetailsEvent) {
        when (movieDetailsEvent) {
            is MovieDetailsEvent.GetMoviesDetails -> getMovieDetails(movieDetailsEvent.movieId)
            is MovieDetailsEvent.GetSimilarMovies -> getSimilarMovies(movieDetailsEvent.movieId)
        }
    }


    fun toggleWatchlist(movie: MovieDetailsModel) {
        viewModelScope.launch {
            if (_isInWatchlist.value) {
                deleteMovieUseCase(movie = movie)
            } else {
                saveMovieUseCase(movie = movie)
            }
            _isInWatchlist.value = !_isInWatchlist.value
        }
    }

//    fun checkWatchlist(movieId: Int) {
//        viewModelScope.launch {
//            _isInWatchlist.value = checkWatchlistUseCase(movieId)
//        }
//    }

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