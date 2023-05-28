package com.ibrahim.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenericError(
    @Json(name = "status_code") val statusCode: String,
    @Json(name = "success") val success: Boolean,
    @Json(name = "status_message") val statusMessage: String
)
