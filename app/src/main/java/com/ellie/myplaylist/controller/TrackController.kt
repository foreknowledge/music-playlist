package com.ellie.myplaylist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Controller
import com.ellie.myplaylist.GlobalApplication
import com.ellie.myplaylist.R
import com.ellie.myplaylist.controller.tracklist.Track
import com.ellie.myplaylist.databinding.ControllerTrackBinding

class TrackController : Controller {
    private lateinit var dataBinding: ControllerTrackBinding

    private var currentTrack: Track? = null

    constructor() : super()

    constructor(args: Bundle) : super(args) {
        val position = args.getInt(KEY_POSITION)
        if (position >= 0) {
            currentTrack = GlobalApplication.playlistProvider.playlist[position]
        }
    }

    constructor(position: Int) : this(Bundle().apply {
        putInt(KEY_POSITION, position)
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.controller_track, container, false)

        setViewsClickListener()

        return dataBinding.root
    }

    private fun setViewsClickListener() {
        with(dataBinding) {
            btnBack.setOnClickListener {
                router.popCurrentController()
            }

            currentTrack?.let {
                track = it
            }
        }
    }

    companion object {
        private const val KEY_POSITION = "position"
    }
}