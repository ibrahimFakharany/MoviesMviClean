package com.ibrahim.moviedetails

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun MovieDetailsScreen(
    movieId: Int?,
    state: MovieDetailsState,
    onEvent: (MovieDetailsEvent) -> Unit
) {
    when (state) {
        MovieDetailsState.LoadingState -> {
            CircularProgressIndicator()
            movieId?.let {
                onEvent(MovieDetailsEvent.LoadData(movieId))
            }
        }

        is MovieDetailsState.LoadingDataSuccess -> {
            Text(state.movie.title)
        }
    }

}
