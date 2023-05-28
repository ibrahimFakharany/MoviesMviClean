package com.ibrahim.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResponse(@Json(name = "genres") val genres: List<Genre>)