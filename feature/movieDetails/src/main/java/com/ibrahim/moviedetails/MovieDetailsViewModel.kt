package com.ibrahim.moviedetails

import com.ibrahim.base.AppResponse
import com.ibrahim.base.BaseViewModel
import com.ibrahim.domain.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) :
    BaseViewModel<MovieDetailsState, MovieDetailsEvent, MovieDetailsEffect>() {


    override fun handleEvents(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.LoadData -> {
                loadData(event.movieId)
            }
        }
    }

    fun loadData(movieId: Int) {
        launchRequest(getMovieDetailsUseCase(movieId),
            onStart = { },
            onComplete = { },
            onSuccess = {
                when (it) {
                    is AppResponse.Success -> {
                        setState { MovieDetailsState.LoadingDataSuccess(it.body) }
                    }

                    else -> {}
                }
            },
            onError = {})
    }

    override fun setInitialState(): MovieDetailsState = MovieDetailsState.LoadingState
}