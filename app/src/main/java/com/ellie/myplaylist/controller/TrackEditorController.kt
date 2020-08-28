package com.ellie.myplaylist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Controller
import com.ellie.myplaylist.GlobalApplication
import com.ellie.myplaylist.R
import com.ellie.myplaylist.controller.tracklist.Track
import com.ellie.myplaylist.databinding.ControllerTrackEditorBinding

class TrackEditorController : Controller {
    private lateinit var dataBinding: ControllerTrackEditorBinding

    private val playlistProvider = GlobalApplication.playlistProvider
    private var trackPosition: Int = -1

    constructor() : super()

    constructor(args: Bundle) : super(args) {
        trackPosition = args.getInt(KEY_POSITION)
    }

    constructor(position: Int) : this(Bundle().apply {
        putInt(KEY_POSITION, position)
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.controller_track_editor, container, false)
        // bind data
        dataBinding.track = if (trackPosition >= 0) playlistProvider.playlist[trackPosition] else null

        setViewsClickListener()

        return dataBinding.root
    }

    private fun setViewsClickListener() {
        with(dataBinding) {
            btnBack.setOnClickListener {
                router.popCurrentController()
            }

            btnSave.setOnClickListener {
                if (checkValid()) {
                    if (trackPosition >= 0) {
                        playlistProvider.updateTrack(trackPosition, makeCurrentTrack())
                    } else {
                        playlistProvider.addTrack(makeCurrentTrack())
                    }

                    Toast.makeText(it.context, it.context.getString(R.string.msg_saved), Toast.LENGTH_SHORT).show()
                    router.popCurrentController()
                } else {
                    Toast.makeText(it.context, it.context.getString(R.string.msg_necessary_field_missed), Toast.LENGTH_LONG).show()
                }
            }

            btnDelete.setOnClickListener {
                playlistProvider.removeTrack(trackPosition)

                Toast.makeText(it.context, it.context.getString(R.string.msg_deleted), Toast.LENGTH_SHORT).show()
                router.popCurrentController()
            }
        }
    }

    private fun checkValid(): Boolean = with(dataBinding) {
        return editTitle.isNotBlank() && editArtist.isNotBlank() && editTime.isNotBlank()
    }

    private fun makeCurrentTrack(): Track = with(dataBinding) {
        Track(
            editTitle.getStr(),
            editArtist.getStr(),
            editTime.getStr(),
            editLyrics.getStr(),
            editIntroduction.getStr()
        )
    }

    private fun EditText.getStr() = text.toString()

    private fun EditText.isNotBlank() = getStr().isNotBlank()

    companion object {
        private const val KEY_POSITION = "position"
    }
}