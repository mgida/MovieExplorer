package com.example.movieexplorer.domain.use_case

import com.example.movieexplorer.domain.mapper.UIPopularMoviesMapper
import com.example.movieexplorer.domain.model.popular_movies.PopularMoviesGroupedByYearModel
import com.example.movieexplorer.domain.repo.MovieRepo
import com.example.movieexplorer.util.moviesGroupedByYear
import com.example.movieexplorer.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetPopularMoviesGroupedByYearByUseCase @Inject constructor(
    private val repo: MovieRepo,
    private val uiPopularMoviesMapper: UIPopularMoviesMapper
) {
    operator fun invoke(): Flow<Resource<List<PopularMoviesGroupedByYearModel>>> = flow {
        emit(Resource.Loading())

        try {
            val popularMoviesResponse = repo.getPopularMovies().results

            popularMoviesResponse?.let {
                val mappedMovies = uiPopularMoviesMapper.map(it)

                val groupedByYearMovies = mutableListOf<PopularMoviesGroupedByYearModel>()

                mappedMovies.moviesGroupedByYear().forEach { (year, moviesInYear) ->
                    groupedByYearMovies.add(
                        PopularMoviesGroupedByYearModel(
                            year = year, movies = moviesInYear
                        )
                    )
                }

                emit(Resource.Success(data = groupedByYearMovies))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage.orEmpty()))
        }
    }
}
