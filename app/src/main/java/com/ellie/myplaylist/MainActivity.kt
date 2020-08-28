package com.ellie.myplaylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.ellie.myplaylist.extractor.PlaylistDataManager
import com.ellie.myplaylist.controller.PlaylistController
import com.ellie.myplaylist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //----------------------------------------------------------
    // Instance data.
    //

    private lateinit var router: Router

    //----------------------------------------------------------
    // Public interface.
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPlaylistDataManager()

        router = Conductor.attachRouter(this, binding.container, savedInstanceState)
        if (!router.hasRootController()) {
            // PlaylistController 시작
            router.setRoot(RouterTransaction.with(PlaylistController()))
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    private fun initPlaylistDataManager() {
        playlistDataManager = PlaylistDataManager(this).apply {
            initPlaylist()
        }
    }

    companion object {
        lateinit var playlistDataManager: PlaylistDataManager
    }
}