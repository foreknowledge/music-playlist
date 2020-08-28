package com.ellie.myplaylist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.ellie.myplaylist.util.PlaylistDataProvider
import com.ellie.myplaylist.controller.tracklist.TrackListAdapter
import com.ellie.myplaylist.databinding.ControllerPlaylistBinding

/**
 * Playlist 화면 담당 Controller.
 */
class PlaylistController : Controller() {

    //----------------------------------------------------------
    // Instance data.
    //

    private lateinit var viewBinding: ControllerPlaylistBinding

    // 리스트로 보여줄 track 리스트를 관리하는 어댑터
    private lateinit var trackListAdapter: TrackListAdapter

    //----------------------------------------------------------
    // Public interface.
    //

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        viewBinding = ControllerPlaylistBinding.inflate(inflater, container, false)

        // 초기 화면 설정
        initTrackList()
        initViews()
        setClickListeners()

        return viewBinding.root
    }

    //----------------------------------------------------------
    // Internal support interface.
    //

    /**
     * Track 리스트를 관리하는 어댑터를 초기화 한다.
     */
    private fun initTrackList() {
        trackListAdapter = TrackListAdapter().apply {
            // 기존 데이터 가져와서 설정
            setPlaylist(PlaylistDataProvider.playlist)

            // 리스트의 아이템 클릭 리스너 설정
            setOnItemClickListener { position ->
                // Track Edit Controller 시작
                // 클릭한 아이템의 position 전달
                router.pushController(RouterTransaction.with(TrackEditorController(position)))
            }

            // 리스트의 Play 버튼 클릭 리스너 설정
            setOnPlayButtonClickListener { position ->
                // Player Controller 시작
                // 클릭한 아이템의 position 전달
                router.pushController(RouterTransaction.with(PlayerController(position)))
            }
        }
    }

    /**
     * View들을 초기화 한다.
     */
    private fun initViews() {
        with(viewBinding) {
            // Playlist의  총 트랙 개수 & 총 재생 시간 설정
            textTotalTrackNum.text = trackListAdapter.itemCount.toString()
            textTotalPlayTime.text = trackListAdapter.totalPlayTimeText
        }

        // RecyclerView 초기화
        with(viewBinding.trackList) {
            layoutManager = LinearLayoutManager(context)
            adapter = trackListAdapter
        }
    }

    /**
     * View들의 클릭 리스너를 설정한다.
     */
    private fun setClickListeners() {
        // Add 버튼 클릭 리스너 설정
        viewBinding.btnAdd.setOnClickListener {
            // Track Edit Controller 시작
            router.pushController(RouterTransaction.with(TrackEditorController()))
        }
    }
}