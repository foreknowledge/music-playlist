package com.ellie.myplaylist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluelinelabs.conductor.Controller
import com.ellie.myplaylist.GlobalApplication
import com.bluelinelabs.conductor.RouterTransaction
import com.ellie.myplaylist.controller.tracklist.TrackListAdapter
import com.ellie.myplaylist.databinding.ControllerPlaylistBinding

class PlaylistController : Controller() {
    private lateinit var viewBinding: ControllerPlaylistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        viewBinding = ControllerPlaylistBinding.inflate(inflater, container, false)
        initPlayList()

        return viewBinding.root
    }

    private fun initPlayList() {
        with(viewBinding.trackList) {
            layoutManager = LinearLayoutManager(context)
            adapter = TrackListAdapter().apply {
                setPlaylist(GlobalApplication.playlistProvider.playlist)
                setOnContainerClickListener { position -> startTrackController(position) }
                setOnPlayButtonClickListener { position -> startPlayerController(position) }
            }
        }
    }

    private fun startTrackController(position: Int) {
        router.pushController(RouterTransaction.with(TrackController()))
    }

    private fun startPlayerController(position: Int) {
        router.pushController(RouterTransaction.with(PlayerController()))
    }
}