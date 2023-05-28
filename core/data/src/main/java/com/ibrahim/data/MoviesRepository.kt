package com.ibrahim.data

import com.ibrahim.base.AppResponseWithGenericError
import com.ibrahim.base.FullMovie
import com.ibrahim.base.GenreResponse
import com.ibrahim.base.MoviesResponse
import com.ibrahim.data.remoteDataRepo.IRemoteDataRepository
import com.ibrahim.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val remoteDataSource: IRemoteDataRepository
) : IMoviesRepository {
    override fun getGenres(): Flow<AppResponseWithGenericError<GenreResponse>> {
        return remoteDataSource.getGenres()
    }

    override fun getMovies(page: Int): Flow<AppResponseWithGenericError<MoviesResponse>> {
        return remoteDataSource.getMovies(page)
    }

    override fun getMovieDetails(movieId: Int): Flow<AppResponseWithGenericError<FullMovie>> {
        return remoteDataSource.getMovieDetails(movieId)
    }

    override fun searchMovies(
        query: String,
        page: Int
    ): Flow<AppResponseWithGenericError<MoviesResponse>> {
        return remoteDataSource.search(query, page)

    }
}