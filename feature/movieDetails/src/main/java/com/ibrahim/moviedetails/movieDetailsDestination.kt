package com.ibrahim.moviedetails

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibrahim.base.SIDE_EFFECT_KEY

@Composable
fun MovieDetailsDestination(movieId: Int? = 0, viewModel: MovieDetailsViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(SIDE_EFFECT_KEY) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MovieDetailsEffect.Error -> {
                    Toast.makeText(context, effect.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    MovieDetailsScreen(movieId, state, viewModel::setEvent)
}