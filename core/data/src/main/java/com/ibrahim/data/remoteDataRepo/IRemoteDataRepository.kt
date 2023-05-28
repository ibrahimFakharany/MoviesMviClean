package com.ibrahim.data.remoteDataRepo

import com.ibrahim.base.AppResponseWithGenericError
import com.ibrahim.base.FullMovie
import com.ibrahim.base.GenreResponse
import com.ibrahim.base.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface IRemoteDataRepository {
    fun getGenres(): Flow<AppResponseWithGenericError<GenreResponse>>
    fun getMovies(page: Int): Flow<AppResponseWithGenericError<MoviesResponse>>
    fun getMovieDetails(movieId: Int): Flow<AppResponseWithGenericError<FullMovie>>
    fun search(query: String, page: Int): Flow<AppResponseWithGenericError<MoviesResponse>>
}