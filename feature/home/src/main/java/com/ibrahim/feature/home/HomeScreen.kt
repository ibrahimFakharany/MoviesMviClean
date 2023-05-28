package com.ibrahim.feature.home

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.ibrahim.base.SIDE_EFFECT_KEY


@Composable
fun HomeScreen(viewModel: HomeViewModel, onNavigateToDetails: (Int) -> Unit) {
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(SIDE_EFFECT_KEY) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeContract.HomeSideEffect.Navigation.ToMovieDetails -> onNavigateToDetails(
                    effect.movieId
                )

                is HomeContract.HomeSideEffect.Error -> {
                    Toast.makeText(context, effect.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    HomeContent(state, viewModel::setEvent)

}