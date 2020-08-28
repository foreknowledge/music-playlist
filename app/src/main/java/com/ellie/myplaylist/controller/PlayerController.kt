package com.ellie.myplaylist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Controller
import com.ellie.myplaylist.util.PlaylistDataProvider
import com.ellie.myplaylist.R
import com.ellie.myplaylist.databinding.ControllerPlayerBinding

/**
 * Player 화면 담당 Controller.
 */
class PlayerController : Controller {

    //----------------------------------------------------------
    // Instance data.
    //

    private lateinit var dataBinding: ControllerPlayerBinding

    // 현재 track의 리스트 index
    private var index: Int

    //----------------------------------------------------------
    // Public interface.
    //

    constructor(args: Bundle) : super(args) {
        // track index를 넘어온 args의 값으로 설정
        index = args.getInt(KEY_POSITION)
    }

    // 넘어온 position을 args 생성자로 전달
    constructor(position: Int) : this(Bundle().apply {
        putInt(KEY_POSITION, position)
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.controller_player, container, false)

        // 초기 화면 설정
        bindData()
        setViewsClickListener()

        return dataBinding.root
    }

    //----------------------------------------------------------
    // Internal support interface.
    //

    /**
     * track, nextTrack 데이터를 View와 바인딩 한다.
     */
    private fun bindData() {
        dataBinding.track = PlaylistDataProvider.getTrack(index)
        dataBinding.nextTrack = PlaylistDataProvider.getNextTrack(index)
    }

    /**
     * 화면 view들의 클릭 리스너를 설정한다.
     */
    private fun setViewsClickListener() {
        with(dataBinding) {
            btnDown.setOnClickListener {
                // Controller 종료
                router.popCurrentController()
            }
        }
    }

    companion object {
        // args bundle에 사용 되는 key 값
        private const val KEY_POSITION = "position"
    }
}