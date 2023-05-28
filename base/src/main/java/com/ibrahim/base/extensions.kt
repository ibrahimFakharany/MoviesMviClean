package com.ibrahim.base

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.compositionLocalOf

typealias AppResponseWithGenericError<S> = AppResponse<S, GenericError>


data class AppState(val scaffoldState: ScaffoldState?) {

}

val AppStateLocal = compositionLocalOf {
    AppState(null)
}