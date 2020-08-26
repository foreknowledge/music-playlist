package com.ellie.myplaylist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Controller
import com.ellie.myplaylist.R
import com.ellie.myplaylist.databinding.ControllerTrackBinding

class TrackController : Controller() {
    private lateinit var dataBinding: ControllerTrackBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.controller_track, container, false)

        return dataBinding.root
    }
}