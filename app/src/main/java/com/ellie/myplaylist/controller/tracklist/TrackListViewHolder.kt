package com.ellie.myplaylist.controller.tracklist

import androidx.recyclerview.widget.RecyclerView
import com.ellie.myplaylist.databinding.ItemTrackBinding

class TrackListViewHolder(private val binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(position: Int, track: Track) {
        binding.number = position + 1
        binding.track = track
    }
}