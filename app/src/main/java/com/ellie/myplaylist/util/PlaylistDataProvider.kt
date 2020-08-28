package com.ellie.myplaylist.util

import com.ellie.myplaylist.app.GlobalApplication
import com.ellie.myplaylist.controller.tracklist.Track

/**
 * playlist 데이터를 제공하는 Singleton Object.
 */
object PlaylistDataProvider {

    //----------------------------------------------------------
    // Instance data.
    //

    private val playlistDataManager = GlobalApplication.playlistDataManager

    //----------------------------------------------------------
    // Public interface.
    //

    val playlist = playlistDataManager.playlist

    /**
     * track list 중에서 position에 해당하는 track을 반환한다.
     * 없는 position가 넘어오면 null을 반환한다.
     */
    fun getTrack(position: Int): Track? {
        return if (position >= 0 && position < playlist.size) {
            playlist[position]
        } else {
            null
        }
    }

    /**
     * track list 중에서 position에 해당하는 track을 반환한다.
     * 다음 Track이 없으면 null을 반환한다.
     */
    fun getNextTrack(position: Int): Track? {
        return if (position >= 0 && position < playlist.size - 1) {
            playlist[position + 1]
        } else {
            null
        }
    }

    fun addTrack(track: Track) = playlistDataManager.addTrack(track)

    fun removeTrack(position: Int) = playlistDataManager.removeTrack(position)

    fun updateTrack(position: Int, track: Track) = playlistDataManager.updateTrack(position, track)
}