package com.ellie.myplaylist.controller.tracklist

import androidx.recyclerview.widget.RecyclerView
import com.ellie.myplaylist.databinding.ItemTrackBinding

class TrackListViewHolder(private val binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(track: Track, onContainerClick: () -> Unit, onPlayButtonClick: () -> Unit) {
        binding.track = track
        binding.trackContainer.setOnClickListener { onContainerClick() }
        binding.btnPlay.setOnClickListener { onPlayButtonClick() }
    }
}