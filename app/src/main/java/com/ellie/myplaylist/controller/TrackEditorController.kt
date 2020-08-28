package com.ellie.myplaylist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Controller
import com.ellie.myplaylist.GlobalApplication
import com.ellie.myplaylist.PlaylistDataProvider
import com.ellie.myplaylist.R
import com.ellie.myplaylist.controller.tracklist.Track
import com.ellie.myplaylist.databinding.ControllerTrackEditorBinding

class TrackEditorController : Controller {
    private lateinit var dataBinding: ControllerTrackEditorBinding

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
        dataBinding.track = PlaylistDataProvider.getTrack(trackPosition)

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
                        PlaylistDataProvider.updateTrack(trackPosition, makeCurrentTrack())
                    } else {
                        PlaylistDataProvider.addTrack(makeCurrentTrack())
                    }

                    it.showToast(R.string.msg_saved)
                    router.popCurrentController()
                } else {
                    it.showToast(R.string.msg_necessary_field_missed, Toast.LENGTH_LONG)
                }
            }

            btnDelete.setOnClickListener {
                PlaylistDataProvider.removeTrack(trackPosition)

                it.showToast(R.string.msg_deleted)
                router.popCurrentController()
            }
        }
    }

    private fun View.showToast(@StringRes resId: Int, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, context.getString(resId), length).show()
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