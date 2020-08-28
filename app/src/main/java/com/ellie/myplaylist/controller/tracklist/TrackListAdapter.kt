package com.ellie.myplaylist.controller.tracklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellie.myplaylist.R
import com.ellie.myplaylist.listener.OnItemClickListener

class TrackListAdapter : RecyclerView.Adapter<TrackListViewHolder>() {
    private val trackList = mutableListOf<Track>()

    private var onContainerClickListener: OnItemClickListener? = null
    private var onPlayButtonClickListener: OnItemClickListener? = null

    val totalPlayTimeText: String
        get() = calcTotalPlayTime()

    fun setPlaylist(tracks: List<Track>) {
        trackList.addAll(tracks)
        notifyDataSetChanged()
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
            position,
            trackList[position],
            onContainerClick = { onContainerClickListener?.onItemClick(position) },
            onPlayButtonClick = { onPlayButtonClickListener?.onItemClick(position) }
        )
    }

    override fun getItemCount() = trackList.size

    private fun calcTotalPlayTime(): String {
        val totalTimeSec = trackList.map {
            val time = it.playTime.split(":")
            val minutes = time[0].toInt()
            val seconds = time[1].toInt()
            minutes * 60 + seconds
        }.sum()

        val minutes = totalTimeSec / 60
        val seconds = totalTimeSec % 60

        val textMinutes = if (minutes < 10) "0$minutes" else minutes.toString()
        val textSecond = if (seconds < 10) "0$seconds" else seconds.toString()

        return "$textMinutes:$textSecond"
    }
}