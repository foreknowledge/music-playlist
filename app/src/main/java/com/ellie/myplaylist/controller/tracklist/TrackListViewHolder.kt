package com.ellie.myplaylist.controller.tracklist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_track.view.*

class TrackListViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    private val textTrackNum = rootView.trackNumber
    private val textTitle = rootView.musicTitle
    private val textDuration = rootView.duration

    fun bind(track: Track) {
        textTrackNum.text = track.number.toString()
        textTitle.text = track.title
        textDuration.text = track.duration
    }
}