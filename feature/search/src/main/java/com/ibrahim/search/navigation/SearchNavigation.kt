package com.ibrahim.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ibrahim.search.SearchDestination

const val SEARCH_ROUTE = "search_route"


fun NavController.navigateToSearch() {
    this.navigate(SEARCH_ROUTE)
}

fun NavGraphBuilder.searchNavigation(navigateToMovieDetails: (Int) -> Unit) {
    composable(SEARCH_ROUTE) {
        SearchDestination(onNavigateToDetails = navigateToMovieDetails)
    }
}