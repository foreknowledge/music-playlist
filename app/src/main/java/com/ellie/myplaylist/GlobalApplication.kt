package com.ellie.myplaylist

import android.app.Application
import android.content.Context
import com.ellie.myplaylist.controller.tracklist.Track
import com.ellie.myplaylist.util.FileAccessor
import com.ellie.myplaylist.util.JsonConverter
import com.squareup.moshi.Types

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        playlistDataManager = PlaylistDataManager(appContext).apply {
            initPlaylist()
        }
    }

    class PlaylistDataManager(context: Context) {
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

    companion object {
        lateinit var appContext: Context
        lateinit var playlistDataManager: PlaylistDataManager
    }
}