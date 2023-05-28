package com.ibrahim.remotedatasource.service

import com.ibrahim.base.AppResponseWithGenericError
import com.ibrahim.base.FullMovie
import com.ibrahim.base.GenreResponse
import com.ibrahim.base.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("genre/movie/list")
    suspend fun getGenres(): AppResponseWithGenericError<GenreResponse>

    @GET("discover/movie")
    suspend fun getMovies(@Query("page") page: Int = 1): AppResponseWithGenericError<MoviesResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): AppResponseWithGenericError<FullMovie>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String, @Query("page") page: Int = 1
    ): AppResponseWithGenericError<MoviesResponse>
}