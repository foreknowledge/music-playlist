package com.ellie.myplaylist.util

import android.content.Context
import com.ellie.myplaylist.controller.tracklist.Track
import com.squareup.moshi.Types

class PlaylistProvider(context: Context) {
    private val fileName = "playlist.json"
    private val fileAccessor = FileAccessor(context, fileName)

    private val trackListType = Types.newParameterizedType(List::class.java, Track::class.java)

    val playlist = mutableListOf<Track>()

    fun initPlaylist() {
        val jsonString = fileAccessor.readData()

        val initPlaylistData = JsonConverter.jsonToObject<List<Track>>(jsonString, trackListType) ?: emptyList()
        playlist.addAll(initPlaylistData)
    }

    fun addTrack(track: Track) {
        playlist.add(track)
    }

    fun removeTrack(position: Int) {
        playlist.removeAt(position)
    }

    fun updateTrack(position: Int, track: Track) {
        playlist[position] = track
    }

    fun saveToJsonFile() {
        val jsonString = JsonConverter.listToJson(playlist, trackListType)
        fileAccessor.writeData(jsonString)
    }
}