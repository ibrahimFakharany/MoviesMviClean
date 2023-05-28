package com.ibrahim.search

import com.ibrahim.base.Movie
import com.ibrahim.base.ViewEvent
import com.ibrahim.base.ViewSideEffect
import com.ibrahim.base.ViewState

class SearchContract {
    sealed class SearchEvent : ViewEvent {
        class OnSearch(val query: String) : SearchEvent()
        class OnMovieClicked(val movieId: Long) : SearchEvent()
        class LoadMore(val page: Int) : SearchEvent()
    }


    sealed class SearchState : ViewState {
        object InitialState : SearchState()
        object LoadingState : SearchState()
        data class LoadingSearchResultDataSuccess(
            val movies: List<Movie>, val page: Int, val totalPages: Int
        ) : SearchState()
    }

    sealed class SearchSideEffect : ViewSideEffect {
        sealed class Navigation : SearchSideEffect() {
            data class ToMovieDetails(
                val movieId: Long,
            ) : Navigation()
        }

        class Error(val message: String) : SearchSideEffect()
    }
}