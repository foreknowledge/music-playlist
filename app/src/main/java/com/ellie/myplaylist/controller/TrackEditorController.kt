package com.ellie.myplaylist.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Controller
import com.ellie.myplaylist.util.PlaylistDataProvider
import com.ellie.myplaylist.R
import com.ellie.myplaylist.controller.tracklist.Track
import com.ellie.myplaylist.databinding.ControllerTrackEditorBinding

/**
 * Track Editor 화면 담당 Controller.
 */
class TrackEditorController : Controller {

    //----------------------------------------------------------
    // Instance data.
    //

    private lateinit var dataBinding: ControllerTrackEditorBinding

    // 현재 track의 리스트 position
    // 새로 만드는 Track일 경우 -1로 설정
    private var position: Int = -1

    //----------------------------------------------------------
    // Public interface.
    //

    constructor() : super()

    constructor(args: Bundle) : super(args) {
        // 현재 track의 position을 넘어온 args의 값으로 설정
        position = args.getInt(KEY_POSITION)
    }

    // 넘어온 position을 args 생성자로 전달
    constructor(position: Int) : this(Bundle().apply {
        putInt(KEY_POSITION, position)
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.controller_track_editor, container, false)

        // 초기 화면 설정
            // position에 해당하는 Track을 가져와서 View와 바인딩
        dataBinding.track = PlaylistDataProvider.getTrack(position)
        setViewsClickListener()

        return dataBinding.root
    }

    //----------------------------------------------------------
    // Internal support interface.
    //

    /**
     * 화면 view들의 클릭 리스너를 설정한다.
     */
    private fun setViewsClickListener() {
        with(dataBinding) {
            btnBack.setOnClickListener {
                // Controller 종료
                router.popCurrentController()
            }

            btnSave.setOnClickListener {
                if (checkValid()) {
                    // 누락된 값이 없는 경우
                    if (position >= 0) {
                        // 기존에 있는 Track인 경우 update.
                        PlaylistDataProvider.updateTrack(position, makeCurrentTrack())
                    } else {
                        // 새로 만든 Track인 경우 add.
                        PlaylistDataProvider.addTrack(makeCurrentTrack())
                    }

                    // Toast 메시지 보여주고 Controller 종료
                    it.showToast(R.string.msg_saved)
                    router.popCurrentController()
                } else {
                    // 누락된 값이 있는 경우 알림 토스트 메시지 보여주기
                    it.showToast(R.string.msg_necessary_field_missed, Toast.LENGTH_LONG)
                }
            }

            btnDelete.setOnClickListener {
                // 현재 position에 해당하는 Track 삭제
                PlaylistDataProvider.removeTrack(position)

                // Toast 메시지 보여주고 Controller 종료
                it.showToast(R.string.msg_deleted)
                router.popCurrentController()
            }
        }
    }

    /**
     * 필수 항목(제목, 아티스트, 재생 시간)이 누락되지 않았는지 검사한다.
     */
    private fun checkValid(): Boolean = with(dataBinding) {
        return editTitle.isNotBlank() && editArtist.isNotBlank() && editTime.isNotBlank()
    }

    /**
     * 화면에 입력 데이터를 가지고 Track 객체를 만든다.
     */
    private fun makeCurrentTrack(): Track = with(dataBinding) {
        Track(
            editTitle.getStr(),
            editArtist.getStr(),
            editTime.getStr(),
            editLyrics.getStr(),
            editDescription.getStr()
        )
    }

    //----------------------------------------------------------
    // View Extension Functions.
    //

    private fun View.showToast(@StringRes resId: Int, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, context.getString(resId), length).show()
    }

    private fun EditText.getStr() = text.toString()

    private fun EditText.isNotBlank() = getStr().isNotBlank()

    companion object {
        // args bundle에 사용 되는 key 값
        private const val KEY_POSITION = "position"
    }
}