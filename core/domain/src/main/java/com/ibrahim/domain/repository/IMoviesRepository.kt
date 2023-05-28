package com.ibrahim.domain.repository

import com.ibrahim.base.AppResponseWithGenericError
import com.ibrahim.base.FullMovie
import com.ibrahim.base.GenreResponse
import com.ibrahim.base.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    fun getGenres(): Flow<AppResponseWithGenericError<GenreResponse>>
    fun getMovies(page: Int): Flow<AppResponseWithGenericError<MoviesResponse>>
    fun searchMovies(query: String, page: Int): Flow<AppResponseWithGenericError<MoviesResponse>>
    fun getMovieDetails(movieId: Int): Flow<AppResponseWithGenericError<FullMovie>>
}