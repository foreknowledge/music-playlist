package com.ellie.myplaylist.app

import android.app.Application
import android.content.Context

/**
 * 애플리케이션 시작 시 생성되는 Application Instance.
 * 시작 시 초기화해야 하는 로직을 가지고 있다.
 */
class GlobalApplication : Application() {
    companion object {
        lateinit var appContext: Context
        lateinit var playlistDataManager: PlaylistDataManager
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext

        // Playlist Data Manager 생성 및 초기화
        playlistDataManager = PlaylistDataManager(appContext).apply {
            initPlaylist()
        }
    }
}