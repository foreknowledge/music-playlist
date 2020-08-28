package com.ellie.myplaylist.controller.tracklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellie.myplaylist.R
import com.ellie.myplaylist.listener.OnItemClickListener

class TrackListAdapter : RecyclerView.Adapter<TrackListViewHolder>() {

    //----------------------------------------------------------
    // Instance data.
    //

    // 리스트로 보여줄 Tracks 데이터
    private val tracks = mutableListOf<Track>()

    // 리스트 아이템 클릭 리스너
    private var onItemClickListener: OnItemClickListener? = null

    // 아이템 안에 있는 Play 버튼 클릭 리스너
    private var onPlayButtonClickListener: OnItemClickListener? = null

    //----------------------------------------------------------
    // Public interface.
    //

    // 리스트에 있는 모든 track의 총 play time
    val totalPlayTimeText: String
        get() = calcTotalPlayTime()

    /**
     * 리스트로 보여줄 playlist 데이터를 설정한다.
     */
    fun setPlaylist(tracks: List<Track>) {
        this.tracks.addAll(tracks)
        notifyDataSetChanged()
    }

    /**
     * 리스트 아이템 클릭 리스너를 설정한다.
     */
    fun setOnItemClickListener(onClick: (position: Int) -> Unit) {
        onItemClickListener = object: OnItemClickListener {
            override fun onItemClick(position: Int) {
                onClick(position)
            }
        }
    }

    /**
     * 아이템 안에 있는 Play 버튼 클릭 리스너를 설정한다.
     */
    fun setOnPlayButtonClickListener(onClick: (position: Int) -> Unit) {
        onPlayButtonClickListener = object: OnItemClickListener {
            override fun onItemClick(position: Int) {
                onClick(position)
            }
        }
    }

    /**
     * ViewHolder를 생성한다.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrackListViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_track, parent, false)
    )

    /**
     * ViewHolder에 데이터를 Binding 한다.
     */
    override fun onBindViewHolder(holder: TrackListViewHolder, position: Int) {
        return holder.bind(
            position,
            tracks[position],
            onItemClick = { onItemClickListener?.onItemClick(position) },
            onPlayButtonClick = { onPlayButtonClickListener?.onItemClick(position) }
        )
    }

    /**
     * 리스트 아이템 개수를 반환한다.
     */
    override fun getItemCount() = tracks.size

    //----------------------------------------------------------
    // Internal support interface.
    //

    private fun calcTotalPlayTime(): String {
        val totalTimeSec = tracks.map {
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