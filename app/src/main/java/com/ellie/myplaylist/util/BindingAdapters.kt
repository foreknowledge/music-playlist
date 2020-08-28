package com.ellie.myplaylist.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ellie.myplaylist.R
import com.ellie.myplaylist.controller.tracklist.Track

/**
 * Data Binding에 필요한 BindingAdapters.
 */

/**
 * 다음 재생 곡의 Text를 "title - artist"로 가공해서 설정한다.
 * 다음 곡이 없다면 안내 문구로 설정한다.
 */
@BindingAdapter("text")
fun TextView.setNextTrackText(nextTrack: Track?) {
    val textNextTrack = if (nextTrack != null) {
        "${nextTrack.title} - ${nextTrack.artist}"
    } else {
        context.getString(R.string.str_no_next_track)
    }

    text = textNextTrack
}