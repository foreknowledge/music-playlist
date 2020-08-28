package com.ellie.myplaylist.controller.tracklist

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Track(
    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "artist")
    val artist: String,

    @field:Json(name = "play_time")
    val playTime: String,

    @field:Json(name = "lyrics")
    val lyrics: String? = null,

    @field:Json(name = "introduction")
    val introduction: String? = null
)