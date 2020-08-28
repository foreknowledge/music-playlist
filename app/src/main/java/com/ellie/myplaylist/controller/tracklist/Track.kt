package com.ellie.myplaylist.controller.tracklist

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Track(
    // 곡 제목
    @field:Json(name = "title")
    val title: String,

    // 아티스트
    @field:Json(name = "artist")
    val artist: String,

    // 곡 재생 시간
    @field:Json(name = "play_time")
    val playTime: String,

    // 가사
    @field:Json(name = "lyrics")
    val lyrics: String? = null,

    // 곡에 대한 설명
    @field:Json(name = "description")
    val description: String? = null
)