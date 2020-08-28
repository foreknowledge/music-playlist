package com.ellie.myplaylist.app

import android.app.Application
import android.content.Context

class GlobalApplication : Application() {
    companion object {
        lateinit var appContext: Context
        lateinit var playlistDataManager: PlaylistDataManager
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        playlistDataManager = PlaylistDataManager(appContext).apply {
            initPlaylist()
        }
    }
}