package com.ellie.myplaylist.controller.tracklist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ellie.myplaylist.R

class TrackListAdapter(context: Context) : RecyclerView.Adapter<TrackListViewHolder>() {
    private val trackList = mutableListOf<Track>()

    init {
        val tracks = context.resources.getStringArray(R.array.tracks)
        for ((i, track) in tracks.withIndex()) {
            val item = track.split("|")
            trackList.add(Track(i + 1, item[0], item[1]))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackListViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TrackListViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: TrackListViewHolder, position: Int) = holder.bind(trackList[position])

    override fun getItemCount() = trackList.size
}