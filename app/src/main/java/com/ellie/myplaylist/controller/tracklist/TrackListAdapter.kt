package com.ellie.myplaylist.controller.tracklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellie.myplaylist.R

class TrackListAdapter : RecyclerView.Adapter<TrackListViewHolder>() {
    private val trackList = mutableListOf<Track>()

    fun setPlaylist(tracks: List<Track>) {
        trackList.addAll(tracks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrackListViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_track, parent, false)
    )

    override fun onBindViewHolder(holder: TrackListViewHolder, position: Int) = holder.bind(position, trackList[position])

    override fun getItemCount() = trackList.size
}