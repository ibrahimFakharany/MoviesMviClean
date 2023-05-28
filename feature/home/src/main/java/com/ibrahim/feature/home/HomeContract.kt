package com.ibrahim.feature.home

import com.ibrahim.base.Genre
import com.ibrahim.base.Movie
import com.ibrahim.base.ViewEvent
import com.ibrahim.base.ViewSideEffect
import com.ibrahim.base.ViewState

class HomeContract {
    sealed class HomeEvent : ViewEvent {
        class OnGenreClicked(val id: Int) : HomeEvent()
        class OnMovieClicked(val movieId: Long) : HomeEvent()
        class LoadMore(val page: Int) : HomeEvent()

    }

    sealed class HomeState : ViewState {
        object LoadingState : HomeState()
        data class HomeLoadedSuccessState(
            val genres: List<Genre> = emptyList(),
            val movies: List<Movie> = emptyList(),
            val selectedGenre: Int = -1,
            val page: Int = 0,
            val totalPages: Int = 0
        ) : HomeState()

        object Error : HomeState()
    }

    sealed class HomeSideEffect : ViewSideEffect {
        sealed class Navigation : HomeSideEffect() {
            data class ToMovieDetails(
                val movieId: Int,
            ) : Navigation()
        }

        class Error(val error: String) : HomeSideEffect()
    }
}