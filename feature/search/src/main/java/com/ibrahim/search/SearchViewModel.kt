package com.ibrahim.search

import androidx.compose.runtime.mutableStateOf
import com.ibrahim.base.AppResponse
import com.ibrahim.base.BaseViewModel
import com.ibrahim.base.Movie
import com.ibrahim.domain.GetSearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase
) : BaseViewModel<SearchContract.SearchState, SearchContract.SearchEvent, SearchContract.SearchSideEffect>() {
    val query = mutableStateOf(String())
    val movies = mutableListOf<Movie>()
    override fun handleEvents(event: SearchContract.SearchEvent) {
        when (event) {
            is SearchContract.SearchEvent.OnSearch -> {
                loadData(event.query)
            }

            is SearchContract.SearchEvent.OnMovieClicked -> {
                setEffect {
                    SearchContract.SearchSideEffect.Navigation.ToMovieDetails(event.movieId)
                }
            }

            is SearchContract.SearchEvent.LoadMore -> {
                loadMoreData(event.page)
            }
        }
    }

    private fun loadData(query: String) {
        launchRequest(getSearchMoviesUseCase(query), onStart = {
            setState {
                SearchContract.SearchState.LoadingState
            }
        }, onComplete = { }, onSuccess = { response ->
            setState {
                SearchContract.SearchState.LoadingSearchResultDataSuccess(
                    movies = (if (response is AppResponse.Success) ((response).body.movies).also {
                        movies.addAll(it)
                    } else emptyList()),
                    totalPages = if (response is AppResponse.Success) response.body.totalPages else 0,
                    page = if (response is AppResponse.Success) response.body.page else 0,
                )
            }
        }, onError = {

        })
    }

    override fun setInitialState(): SearchContract.SearchState =
        SearchContract.SearchState.InitialState

    fun loadMoreData(page: Int) {

        launchRequest(getSearchMoviesUseCase(query.value, page = page),
            onStart = { },
            onComplete = { },
            onSuccess = { response ->
                if (response is AppResponse.Success) movies.addAll(response.body.movies)

                setState {
                    (this as SearchContract.SearchState.LoadingSearchResultDataSuccess).copy(
                        page = page, movies = movies
                    )
                }
            },
            onError = {

            })
    }
}