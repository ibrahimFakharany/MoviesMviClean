package com.ibrahim.base

import java.io.IOException

sealed class AppResponse<out T : Any, out U : Any> {
    data class Success<T : Any>(val body: T) : AppResponse<T, Nothing>()
    data class ApiError<U : Any>(val body: U, val code: Int) : AppResponse<Nothing, U>()
    data class NetworkError(val error: IOException) : AppResponse<Nothing, Nothing>()
    data class UnknownError(val error: Throwable?) : AppResponse<Nothing, Nothing>()
}