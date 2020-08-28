package com.ellie.myplaylist

import com.ellie.myplaylist.controller.tracklist.Track

object PlaylistDataProvider {
    private val playlistDataManager = GlobalApplication.playlistDataManager

    val playlist = playlistDataManager.playlist

    fun getTrack(position: Int): Track? {
        return if (position >= 0 && position < playlist.size) {
            playlist[position]
        } else {
            null
        }
    }

    fun addTrack(track: Track) = playlistDataManager.addTrack(track)

    fun removeTrack(position: Int) = playlistDataManager.removeTrack(position)

    fun updateTrack(position: Int, track: Track) = playlistDataManager.updateTrack(position, track)
}