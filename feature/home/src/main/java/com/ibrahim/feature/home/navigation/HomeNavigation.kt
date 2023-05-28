package com.ibrahim.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ibrahim.feature.home.HomeDestination

const val home_route = "home_route"
const val home_graph = "home_graph"

fun NavGraphBuilder.homeGraph(
    navigateToMovieDetails: (Int) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(route = home_graph, startDestination = home_route) {
        composable(home_route) {
            HomeDestination(onNavigateToDetails = navigateToMovieDetails)
        }
        nestedGraphs()
    }

}