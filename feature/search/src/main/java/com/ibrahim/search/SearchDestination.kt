package com.ibrahim.search

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibrahim.base.SIDE_EFFECT_KEY


@Composable
fun SearchDestination(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigateToDetails: (Int) -> Unit

) {
    val context = LocalContext.current
    LaunchedEffect(SIDE_EFFECT_KEY) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SearchContract.SearchSideEffect.Error -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }

                is SearchContract.SearchSideEffect.Navigation.ToMovieDetails -> {
                    onNavigateToDetails(
                        effect.movieId.toInt()
                    )
                }
            }
        }
    }
    SearchScreen(viewModel)
}
