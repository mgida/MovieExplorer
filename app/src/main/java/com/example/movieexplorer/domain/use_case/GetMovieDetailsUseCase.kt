package com.example.movieexplorer.domain.use_case

import com.example.movieexplorer.data.dto.movie_details.MovieDetailsResponse
import com.example.movieexplorer.domain.repo.MovieRepo
import com.example.movieexplorer.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repo: MovieRepo) {
    operator fun invoke(
        movieId: Int,
    ): Flow<Resource<MovieDetailsResponse>> = flow {
        emit(Resource.Loading())

        try {
            val movieDetails = repo.getMovieDetails(movieId)
            emit(Resource.Success(data = movieDetails))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage.orEmpty()))
        }
    }
}