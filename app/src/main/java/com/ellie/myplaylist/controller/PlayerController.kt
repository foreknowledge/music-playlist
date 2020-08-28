package com.ellie.myplaylist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Controller
import com.ellie.myplaylist.util.PlaylistDataProvider
import com.ellie.myplaylist.R
import com.ellie.myplaylist.databinding.ControllerPlayerBinding

class PlayerController : Controller {
    private lateinit var dataBinding: ControllerPlayerBinding

    private var position: Int

    constructor(args: Bundle) : super(args) {
        position = args.getInt(KEY_POSITION)
    }

    constructor(position: Int) : this(Bundle().apply {
        putInt(KEY_POSITION, position)
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.controller_player, container, false)

        bindData()
        setViewsClickListener()

        return dataBinding.root
    }

    private fun bindData() {
        val playlist = PlaylistDataProvider.playlist
        dataBinding.track = playlist[position]

        if (position < playlist.size - 1) {
            dataBinding.nextTrack = playlist[position + 1]
        }
    }

    private fun setViewsClickListener() {
        with(dataBinding) {
            btnDown.setOnClickListener {
                router.popCurrentController()
            }
        }
    }

    companion object {
        private const val KEY_POSITION = "position"
    }
}