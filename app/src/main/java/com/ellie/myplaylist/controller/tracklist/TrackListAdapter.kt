package com.ellie.myplaylist.controller.tracklist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellie.myplaylist.R

class TrackListAdapter(context: Context) : RecyclerView.Adapter<TrackListViewHolder>() {
    private val trackList = mutableListOf<Track>()

    init {
        val tracks = context.resources.getStringArray(R.array.tracks)
        for (track in tracks) {
            val item = track.split("|")
            trackList.add(Track(item[0], "Chance the Rapper", item[1]))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrackListViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_track, parent, false)
    )

    override fun onBindViewHolder(holder: TrackListViewHolder, position: Int) = holder.bind(position, trackList[position])

    override fun getItemCount() = trackList.size
}