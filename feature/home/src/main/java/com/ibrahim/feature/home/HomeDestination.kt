package com.ibrahim.feature.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun HomeDestination(
    viewModel: HomeViewModel = hiltViewModel(), onNavigateToDetails: (Int) -> Unit
) {
    HomeScreen(viewModel, onNavigateToDetails)
}
