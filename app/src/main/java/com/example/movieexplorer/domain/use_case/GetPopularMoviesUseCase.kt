package com.example.movieexplorer.domain.use_case

import com.example.movieexplorer.data.dto.popular_movies.PopularMoviesResponse
import com.example.movieexplorer.domain.repo.MovieRepo
import com.example.movieexplorer.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repo: MovieRepo) {
    operator fun invoke(): Flow<Resource<PopularMoviesResponse>> = flow {
        emit(Resource.Loading())

        try {
            val popularMovies = repo.getPopularMovies()
            emit(Resource.Success(data = popularMovies))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage.orEmpty()))
        }
    }
}