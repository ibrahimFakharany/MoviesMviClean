package com.ibrahim.search

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.ibrahim.base.composables.MoviesListing

@Composable
fun SearchScreenContent(
    searchState: SearchContract.SearchState, onEvent: (SearchContract.SearchEvent) -> Unit
) {


    var name: String by remember { mutableStateOf(String()) }

    Column {
        OutlinedTextField(
            value = name,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { name = it },
            label = { Text("Search") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(onSearch = {
                onEvent(
                    SearchContract.SearchEvent.OnSearch(name)
                )
            })
        )
        when (searchState) {
            SearchContract.SearchState.InitialState -> {

            }

            SearchContract.SearchState.LoadingState -> {
                CircularProgressIndicator()
            }

            is SearchContract.SearchState.LoadingSearchResultDataSuccess -> {
                MoviesListing(searchState.movies,
                    page = searchState.page,
                    totalPages = searchState.totalPages,
                    onLoadMore = {
                        onEvent(SearchContract.SearchEvent.LoadMore(it + 1))
                    },
                    onMovieClicked = {
                        onEvent(SearchContract.SearchEvent.OnMovieClicked(it))
                    })

            }
        }

    }

}