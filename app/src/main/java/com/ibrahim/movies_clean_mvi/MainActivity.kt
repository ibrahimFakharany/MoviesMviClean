package com.ibrahim.movies_clean_mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ibrahim.base.AppState
import com.ibrahim.base.AppStateLocal
import com.ibrahim.feature.home.navigation.homeGraph
import com.ibrahim.feature.home.navigation.home_graph
import com.ibrahim.feature.home.navigation.home_route
import com.ibrahim.moviedetails.navigation.movieDetailsNavigation
import com.ibrahim.moviedetails.navigation.movie_details_route
import com.ibrahim.moviedetails.navigation.navigateToMovieDetails
import com.ibrahim.movies_clean_mvi.ui.theme.MoviesCleanMviTheme
import com.ibrahim.search.navigation.SEARCH_ROUTE
import com.ibrahim.search.navigation.navigateToSearch
import com.ibrahim.search.navigation.searchNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesCleanMviTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val appState = AppState(scaffoldState)
    val topBarState = rememberSaveable { (mutableStateOf(true)) }

    val currentStack = navController.currentBackStackEntryAsState().value

    when (currentStack?.destination?.route) {
        home_route -> {
            topBarState.value = true
        }

        movie_details_route -> {
            topBarState.value = true
        }

        SEARCH_ROUTE -> {
            topBarState.value = false

        }
    }
    Scaffold(
        modifier = Modifier, topBar = {
            if (topBarState.value) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        navController.navigateToSearch()
                    })
            }
        }, scaffoldState = scaffoldState
    ) { padding ->

        CompositionLocalProvider(AppStateLocal provides appState) {
            NavHost(
                navController = navController,
                startDestination = home_graph,
                modifier = Modifier.padding(padding)
            ) {
                homeGraph(navigateToMovieDetails = {
                    navController.navigateToMovieDetails(it.toString())
                }) {
                    movieDetailsNavigation()
                    searchNavigation {
                        navController.navigateToMovieDetails(it.toString())
                    }
                }
            }
        }

    }
}
