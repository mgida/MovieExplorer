package com.example.movieexplorer.domain.use_case

import com.example.movieexplorer.domain.model.movie_details.MovieDetailsModel
import com.example.movieexplorer.domain.repo.MovieRepo
import com.example.movieexplorer.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetWatchListUseCase @Inject constructor(private val movieRepo: MovieRepo) {
    operator fun invoke(): Flow<Resource<List<MovieDetailsModel>>> =
        flow {
            emit(Resource.Loading())

            try {
                movieRepo.getWatchList().onEach {
                    emit(Resource.Success(it))
                }.collect()

            } catch (e: Exception) {
                emit(Resource.Error(e.message.orEmpty()))
            }
        }
}