package com.ellie.myplaylist

import android.app.Application
import android.content.Context
import com.ellie.myplaylist.util.PlaylistProvider

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        playlistProvider = PlaylistProvider(appContext).apply {
            initPlaylist()
        }
    }

    companion object {
        lateinit var appContext: Context
        lateinit var playlistProvider: PlaylistProvider
    }
}