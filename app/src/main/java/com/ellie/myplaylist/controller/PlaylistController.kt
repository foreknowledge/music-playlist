package com.ellie.myplaylist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluelinelabs.conductor.Controller
import com.ellie.myplaylist.controller.tracklist.TrackListAdapter
import com.ellie.myplaylist.databinding.ControllerPlaylistBinding

class PlaylistController : Controller() {
    private lateinit var viewBinding: ControllerPlaylistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        viewBinding = ControllerPlaylistBinding.inflate(inflater, container, false)
        initTrackList()

        return viewBinding.root
    }

    private fun initTrackList() {
        with(viewBinding.trackList) {
            layoutManager = LinearLayoutManager(context)
            adapter = TrackListAdapter(context)
        }
    }
}