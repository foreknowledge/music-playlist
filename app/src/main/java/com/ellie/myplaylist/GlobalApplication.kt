package com.ellie.myplaylist

import android.app.Application
import android.content.Context
import com.ellie.myplaylist.util.PlaylistDataManager

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        playlistDataManager = PlaylistDataManager(appContext).apply {
            initPlaylist()
        }
    }

    companion object {
        lateinit var appContext: Context
        lateinit var playlistDataManager: PlaylistDataManager
    }
}