package com.ibrahim.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "poster_path") val poster: String?,
    @Json(name = "genre_ids") val genreIds: List<Int>,
)
