package com.example.movieexplorer.presentation.home_movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import com.example.movieexplorer.domain.model.popular_movies.PopularMoviesGroupedByYearModel
import com.example.movieexplorer.domain.use_case.GetMoviesGroupedByYearUseCase
import com.example.movieexplorer.domain.use_case.GetWatchListUseCase
import com.example.movieexplorer.presentation.home_movies.event.SearchMoviesEvent
import com.example.movieexplorer.presentation.home_movies.viewstate.PopularMoviesViewState
import com.example.movieexplorer.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val groupedYearByUseCase: GetMoviesGroupedByYearUseCase,
    private val getWatchListUseCase: GetWatchListUseCase
) : ViewModel() {

    private val _popularMoviesState = MutableStateFlow(PopularMoviesViewState())
    val popularMoviesState: StateFlow<PopularMoviesViewState> = _popularMoviesState

    private val _watchListState = MutableStateFlow<List<MovieDetailsModel>>(emptyList())
    val watchListState: StateFlow<List<MovieDetailsModel>> = _watchListState

    init {
        getPopularMovies()
        getWatchList()
    }


    fun onEvent(searchMoviesEvent: SearchMoviesEvent) {
        when (searchMoviesEvent) {
            is SearchMoviesEvent.GetPopularMovies -> getPopularMovies()
            is SearchMoviesEvent.SearchMovies -> searchMovies(query = searchMoviesEvent.query)
            SearchMoviesEvent.GetWatchList -> getWatchList()
        }
    }

    private fun getWatchList() {
        getWatchListUseCase().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {}
                is Resource.Error -> {}
                is Resource.Success -> {
                    _watchListState.value = resource.data ?: emptyList()
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPopularMovies() {
        groupedYearByUseCase.invoke()
            .onEach { resource: Resource<List<PopularMoviesGroupedByYearModel>> ->
                when (resource) {

                    is Resource.Loading -> {
                        _popularMoviesState.value = PopularMoviesViewState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _popularMoviesState.value =
                            PopularMoviesViewState(error = resource.message.orEmpty())
                    }

                    is Resource.Success -> {
                        _popularMoviesState.value =
                            PopularMoviesViewState(data = resource.data ?: listOf())
                    }
                }

            }.launchIn(viewModelScope)
    }

    private fun searchMovies(query: String) {

        if (query.isBlank()) {
            getPopularMovies()
            return
        }

        groupedYearByUseCase.invoke(query = query)
            .onEach { resource: Resource<List<PopularMoviesGroupedByYearModel>> ->
                when (resource) {

                    is Resource.Loading -> {
                        _popularMoviesState.value = PopularMoviesViewState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _popularMoviesState.value =
                            PopularMoviesViewState(error = resource.message.orEmpty())
                    }

                    is Resource.Success -> {
                        _popularMoviesState.value =
                            PopularMoviesViewState(data = resource.data ?: listOf())
                    }
                }

            }.launchIn(viewModelScope)
    }
}