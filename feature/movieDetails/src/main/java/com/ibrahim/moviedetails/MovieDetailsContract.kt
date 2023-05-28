package com.ibrahim.moviedetails

import com.ibrahim.base.FullMovie
import com.ibrahim.base.ViewEvent
import com.ibrahim.base.ViewSideEffect
import com.ibrahim.base.ViewState

sealed class MovieDetailsEvent : ViewEvent {
    class LoadData(val movieId: Int) : MovieDetailsEvent()
}

sealed class MovieDetailsState : ViewState {
    object LoadingState : MovieDetailsState()
    class LoadingDataSuccess(val movie: FullMovie) : MovieDetailsState()
}

sealed class MovieDetailsEffect : ViewSideEffect {
    class Error(val error: String) : MovieDetailsEffect()
}