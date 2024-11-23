package com.example.movieexplorer.domain.use_case

import com.example.movieexplorer.data.dto.popular_movies.MovieResponse
import com.example.movieexplorer.domain.mapper.UIPopularMoviesMapper
import com.example.movieexplorer.domain.model.popular_movies.PopularMoviesGroupedByYearModel
import com.example.movieexplorer.domain.repo.MovieRepo
import com.example.movieexplorer.util.Resource
import com.example.movieexplorer.util.moviesGroupedByYear
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesGroupedByYearUseCase @Inject constructor(
    private val repo: MovieRepo,
    private val uiPopularMoviesMapper: UIPopularMoviesMapper
) {

    private fun processAndGroupMovies(movies: List<MovieResponse>?): List<PopularMoviesGroupedByYearModel> {
        val mappedMovies = uiPopularMoviesMapper.map(movies ?: emptyList())

        return mappedMovies.moviesGroupedByYear().map { (year, moviesInYear) ->
            PopularMoviesGroupedByYearModel(year = year, movies = moviesInYear)
        }
    }

    operator fun invoke(): Flow<Resource<List<PopularMoviesGroupedByYearModel>>> = flow {
        emit(Resource.Loading())
        try {
            val popularMoviesResponse = repo.getPopularMovies().results
            val groupedByYearMovies = processAndGroupMovies(popularMoviesResponse)
            emit(Resource.Success(data = groupedByYearMovies))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage.orEmpty()))
        }
    }

    operator fun invoke(query: String): Flow<Resource<List<PopularMoviesGroupedByYearModel>>> = flow {
        emit(Resource.Loading())
        try {
            val searchMoviesResponse = repo.searchMovies(query).results
            val groupedByYearMovies = processAndGroupMovies(searchMoviesResponse)
            emit(Resource.Success(data = groupedByYearMovies))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage.orEmpty()))
        }
    }
}
