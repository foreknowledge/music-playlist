package com.ellie.myplaylist.controller.tracklist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellie.myplaylist.R
import com.ellie.myplaylist.listener.OnItemClickListener

class TrackListAdapter(context: Context) : RecyclerView.Adapter<TrackListViewHolder>() {
    private val trackList = mutableListOf<Track>()

    private var onContainerClickListener: OnItemClickListener? = null
    private var onPlayButtonClickListener: OnItemClickListener? = null

    init {
        val tracks = context.resources.getStringArray(R.array.tracks)
        for ((i, track) in tracks.withIndex()) {
            val item = track.split("|")
            trackList.add(Track(i + 1, item[0], "Chance the Rapper", item[1]))
        }
    }

    fun setOnContainerClickListener(onClick: (position: Int) -> Unit) {
        onContainerClickListener = object: OnItemClickListener {
            override fun onItemClick(position: Int) {
                onClick(position)
            }
        }
    }

    fun setOnPlayButtonClickListener(onClick: (position: Int) -> Unit) {
        onPlayButtonClickListener = object: OnItemClickListener {
            override fun onItemClick(position: Int) {
                onClick(position)
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrackListViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_track, parent, false)
    )

    override fun onBindViewHolder(holder: TrackListViewHolder, position: Int) {
        return holder.bind(
            trackList[position],
            onContainerClick = { onContainerClickListener?.onItemClick(position) },
            onPlayButtonClick = { onPlayButtonClickListener?.onItemClick(position)}
        )
    }

    override fun getItemCount() = trackList.size
}