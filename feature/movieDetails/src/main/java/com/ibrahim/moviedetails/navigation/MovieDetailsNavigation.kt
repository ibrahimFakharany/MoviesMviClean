package com.ibrahim.moviedetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ibrahim.moviedetails.MovieDetailsDestination

const val MOVIE_ID_KEY = "movie_id"
const val movie_details_route = "movie_details_route/{${MOVIE_ID_KEY}}"

fun NavController.navigateToMovieDetails(id: String) {
    this.navigate(movie_details_route.replace("{$MOVIE_ID_KEY}", id))
}

fun NavGraphBuilder.movieDetailsNavigation() {
    composable(
        route = movie_details_route,
        arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType })
    ) {
        MovieDetailsDestination(it.arguments?.getInt(MOVIE_ID_KEY))
    }
}