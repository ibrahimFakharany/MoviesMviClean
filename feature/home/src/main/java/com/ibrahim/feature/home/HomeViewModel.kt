package com.ibrahim.feature.home

import com.ibrahim.base.AppResponse
import com.ibrahim.base.BaseViewModel
import com.ibrahim.base.Movie
import com.ibrahim.domain.GetGenresUseCase
import com.ibrahim.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase, private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel<HomeContract.HomeState, HomeContract.HomeEvent, HomeContract.HomeSideEffect>() {
    var originalMovies = mutableListOf<Movie>()

    init {
        loadData()
    }

    override fun handleEvents(event: HomeContract.HomeEvent) {
        when (event) {
            is HomeContract.HomeEvent.OnGenreClicked -> {
                setState {
                    (this as HomeContract.HomeState.HomeLoadedSuccessState).copy(selectedGenre = if (this.selectedGenre == event.id) -1 else event.id,
                        movies = if (this.selectedGenre == event.id) originalMovies else originalMovies.filter { movie ->
                            movie.genreIds.contains(
                                event.id
                            )
                        })
                }
            }

            is HomeContract.HomeEvent.OnMovieClicked -> {
                setEffect {
                    HomeContract.HomeSideEffect.Navigation.ToMovieDetails(event.movieId.toInt())
                }
            }

            is HomeContract.HomeEvent.LoadMore -> {
                loadMoreData(event.page)
            }
        }
    }

    private fun loadData() {
        launchRequest(combine(getGenresUseCase(), getMoviesUseCase()) { genres, movies ->
            Pair(genres, movies)
        }, onStart = {

        }, onComplete = {

        }, onSuccess = {
            setState {
                HomeContract.HomeState.HomeLoadedSuccessState(
                    genres = if (it.first is AppResponse.Success) (it.first as AppResponse.Success).body.genres
                    else emptyList(),
                    movies = (if (it.second is AppResponse.Success) ((it.second as AppResponse.Success).body.movies).also {
                        originalMovies.addAll(it)
                    }
                    else emptyList()),
                    totalPages = if (it.second is AppResponse.Success) ((it.second as AppResponse.Success).body.totalPages) else 0,
                    page = if (it.second is AppResponse.Success) ((it.second as AppResponse.Success).body.page) else 0,
                )
            }
        }, onError = {response ->
            setEffect {
                HomeContract.HomeSideEffect.Error(response)
            }
        })
    }


    fun loadMoreData(page: Int) {

        launchRequest(getMoviesUseCase(page), onStart = { }, onComplete = { }, onSuccess = {
            if (it is AppResponse.Success) originalMovies.addAll(it.body.movies)

            setState {
                (this as HomeContract.HomeState.HomeLoadedSuccessState).copy(page = page,
                    movies = if (this.selectedGenre == -1) originalMovies else originalMovies.filter {
                        it.genreIds.contains(
                            selectedGenre
                        )
                    })
            }
        }, onError = {

        })
    }

    override fun setInitialState() = HomeContract.HomeState.LoadingState
}