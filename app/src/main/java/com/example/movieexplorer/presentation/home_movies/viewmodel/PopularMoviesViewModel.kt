package com.example.movieexplorer.presentation.home_movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieexplorer.domain.model.popular_movies.PopularMoviesGroupedByYearModel
import com.example.movieexplorer.domain.use_case.GetPopularMoviesGroupedByYearByUseCase
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
    private val groupedYearByUseCase: GetPopularMoviesGroupedByYearByUseCase
) : ViewModel() {

    private val _popularMoviesState = MutableStateFlow(PopularMoviesViewState())
    val popularMoviesState: StateFlow<PopularMoviesViewState> = _popularMoviesState

    init {
        getPopularMovies()
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
}