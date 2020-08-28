package com.ellie.myplaylist.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ellie.myplaylist.R
import com.ellie.myplaylist.controller.tracklist.Track

@BindingAdapter("text")
fun TextView.setNextTrackText(nextTrack: Track?) {
    val textNextTrack = if (nextTrack != null) {
        "${nextTrack.title} - ${nextTrack.artist}"
    } else {
        context.getString(R.string.str_no_next_track)
    }

    text = textNextTrack
}