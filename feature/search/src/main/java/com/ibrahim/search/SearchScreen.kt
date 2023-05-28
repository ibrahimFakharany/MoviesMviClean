package com.ibrahim.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState


@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    val state = viewModel.state.collectAsState().value
    SearchScreenContent(state, viewModel::setEvent)
}