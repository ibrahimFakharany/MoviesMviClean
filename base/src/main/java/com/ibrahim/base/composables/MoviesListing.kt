package com.ibrahim.base.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ibrahim.base.AppStateLocal
import com.ibrahim.base.Constants.BASE_MOVIE_POSTER
import com.ibrahim.base.Movie
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MoviesListing(
    movies: List<Movie>,
    page: Int,
    totalPages: Int,
    onMovieClicked: (movieId: Long) -> Unit,
    onLoadMore: (nextPage: Int) -> Unit,
) {
    val state = AppStateLocal.current
    val scope = rememberCoroutineScope()

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {

        itemsIndexed(movies) { index, item ->
            Surface(
                modifier = Modifier.padding(all = 10.dp).combinedClickable(onClick = {
                    onMovieClicked(movies[index].id)
                }, onLongClick = {
                    scope.launch {
                        state.scaffoldState?.snackbarHostState?.showSnackbar(
                            movies[index].title
                        )
                    }
                })
            ) {
                AsyncImage(
                    model = BASE_MOVIE_POSTER + movies[index].poster,
                    contentDescription = null,
                )
            }

            if (totalPages > 1 && index == (movies.size - 5) && page < totalPages) {
                onLoadMore(page + 1)
            }
        }

    }
}