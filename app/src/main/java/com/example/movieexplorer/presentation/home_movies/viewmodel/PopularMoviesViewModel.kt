package com.example.movieexplorer.presentation.home_movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieexplorer.data.dto.popular_movies.PopularMoviesResponse
import com.example.movieexplorer.domain.mapper.UIPopularMoviesMapper
import com.example.movieexplorer.domain.use_case.GetPopularMoviesUseCase
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
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
    private val popularMoviesMapper: UIPopularMoviesMapper
) : ViewModel() {

    private val _popularMoviesState = MutableStateFlow(PopularMoviesViewState())
    val popularMoviesState: StateFlow<PopularMoviesViewState> = _popularMoviesState

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        popularMoviesUseCase.invoke().onEach { resource: Resource<PopularMoviesResponse> ->
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
                        PopularMoviesViewState(
                            data = popularMoviesMapper.map(resource.data?.results ?: listOf())
                        )
                }
            }

        }.launchIn(viewModelScope)
    }
}