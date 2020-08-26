package com.ellie.myplaylist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluelinelabs.conductor.Controller
import com.ellie.myplaylist.R
import com.ellie.myplaylist.controller.tracklist.TrackListAdapter
import kotlinx.android.synthetic.main.controller_playlist.view.*

class PlaylistController : Controller() {
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        rootView = inflater.inflate(R.layout.controller_playlist, container, false)
        initTrackList()

        return rootView
    }

    private fun initTrackList() {
        with(rootView.trackList) {
            layoutManager = LinearLayoutManager(context)
            adapter = TrackListAdapter(context)
        }
    }
}