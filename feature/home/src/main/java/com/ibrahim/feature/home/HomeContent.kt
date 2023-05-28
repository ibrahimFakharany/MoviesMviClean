package com.ibrahim.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ibrahim.base.composables.MoviesListing

@Composable
fun HomeContent(homeState: HomeContract.HomeState, onEvent: (HomeContract.HomeEvent) -> Unit) {
    when (homeState) {
        HomeContract.HomeState.LoadingState -> {
            CircularProgressIndicator()
        }

        is HomeContract.HomeState.HomeLoadedSuccessState -> {
            Column {
                LazyRow {
                    items(homeState.genres.size) { index ->
                        Surface(color = if (homeState.selectedGenre == homeState.genres[index].id) Color.Yellow
                        else Color.White, modifier = Modifier.padding(all = 10.dp).clickable {
                            onEvent(HomeContract.HomeEvent.OnGenreClicked(homeState.genres[index].id))
                        }) {
                            Text(homeState.genres[index].name)
                        }
                    }
                }

                MoviesListing(homeState.movies,
                    page = homeState.page,
                    totalPages = homeState.totalPages,
                    onLoadMore = {
                        onEvent(HomeContract.HomeEvent.LoadMore(it + 1))
                    },
                    onMovieClicked = {
                        onEvent(HomeContract.HomeEvent.OnMovieClicked(it))
                    })
            }
        }

        HomeContract.HomeState.Error -> {}
    }
}

const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500/"