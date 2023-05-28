package com.ibrahim.remotedatasource

import com.ibrahim.base.AppResponseWithGenericError
import com.ibrahim.base.FullMovie
import com.ibrahim.base.GenreResponse
import com.ibrahim.base.MoviesResponse
import com.ibrahim.data.remoteDataRepo.IRemoteDataRepository
import com.ibrahim.remotedatasource.service.MoviesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val apiService: MoviesService
) : IRemoteDataRepository {
    override fun getGenres(): Flow<AppResponseWithGenericError<GenreResponse>> {
        return flow {
            emit(apiService.getGenres())
        }
    }

    override fun getMovies(page: Int): Flow<AppResponseWithGenericError<MoviesResponse>> {
        return flow {
            emit(apiService.getMovies(page))
        }
    }

    override fun getMovieDetails(movieId: Int): Flow<AppResponseWithGenericError<FullMovie>> {
        return flow {
            emit(apiService.getMovieDetails(movieId))
        }
    }

    override fun search(
        query: String,
        page: Int
    ): Flow<AppResponseWithGenericError<MoviesResponse>> {
        return flow {
            emit(apiService.searchMovies(query, page))
        }
    }
}
