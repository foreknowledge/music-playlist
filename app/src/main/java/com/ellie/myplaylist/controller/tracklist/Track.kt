package com.ellie.myplaylist.controller.tracklist

data class Track(
    val title: String,
    val artist: String,
    val playTime: String = "00:00",
    val lyrics: String? = null,
    val introduction: String? = null
)