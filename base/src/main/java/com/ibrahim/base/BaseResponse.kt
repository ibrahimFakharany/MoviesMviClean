package com.ibrahim.base

sealed class BaseResponse<out T> {
    data class Success<T>(val data: T?) : BaseResponse<T>()
    data class Failure(val error: String) : BaseResponse<Nothing>()
}