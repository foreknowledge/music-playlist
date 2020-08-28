package com.ellie.myplaylist.app

import android.content.Context
import com.ellie.myplaylist.controller.tracklist.Track
import com.squareup.moshi.Types

class PlaylistDataManager(context: Context) {
    private val fileName = "playlist.json"
    private val fileAccessor = FileAccessor(context, fileName)

    private val trackListType = Types.newParameterizedType(List::class.java, Track::class.java)

    val playlist = mutableListOf<Track>()

    fun initPlaylist() {
        val jsonString = fileAccessor.readData()

        val initPlaylistData = JsonConverter.jsonToObject<List<Track>>(
            jsonString,
            trackListType
        ) ?: emptyList()
        playlist.addAll(initPlaylistData)
    }

    fun addTrack(track: Track) {
        playlist.add(track)
        saveToJsonFile()
    }

    fun removeTrack(position: Int) {
        playlist.removeAt(position)
        saveToJsonFile()
    }

    fun updateTrack(position: Int, track: Track) {
        playlist[position] = track
        saveToJsonFile()
    }

    private fun saveToJsonFile() {
        val jsonString = JsonConverter.listToJson(playlist, trackListType)
        fileAccessor.writeData(jsonString)
    }
}