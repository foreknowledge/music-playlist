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

    private lateinit var trackListAdapter: TrackListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        viewBinding = ControllerPlaylistBinding.inflate(inflater, container, false)

        initTrackList()
        initViews()
        setAddButtonClickListener()

        return viewBinding.root
    }

    private fun initViews() {
        with(viewBinding) {
            textTotalTrackNum.text = trackListAdapter.itemCount.toString()
            textTotalPlayTime.text = trackListAdapter.totalPlayTimeText
        }

        with(viewBinding.trackList) {
            layoutManager = LinearLayoutManager(context)
            adapter = trackListAdapter
        }
    }

    private fun initTrackList() {
        trackListAdapter = TrackListAdapter().apply {
            setPlaylist(GlobalApplication.playlistProvider.playlist)
            setOnContainerClickListener { position ->
                router.pushController(RouterTransaction.with(TrackEditorController(position)))
            }
            setOnPlayButtonClickListener { position ->
                router.pushController(RouterTransaction.with(PlayerController(position)))
            }
        }
    }

    private fun setAddButtonClickListener() {
        viewBinding.btnAdd.setOnClickListener {
            router.pushController(RouterTransaction.with(TrackEditorController()))
        }
    }
}