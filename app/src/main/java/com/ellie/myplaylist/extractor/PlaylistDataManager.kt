package com.ellie.myplaylist.extractor

import android.content.Context
import com.ellie.myplaylist.controller.tracklist.Track
import com.squareup.moshi.Types

/**
 * Playlist 데이터를 관리한다.
 * json 파일에서 데이터를 가져와 playlist 변수를 초기화 한다.
 * playlist에 Track 데이터를 추가/삭제/변경 하는 메서드를 제공한다.
 */
class PlaylistDataManager(context: Context) {

    //----------------------------------------------------------
    // Instance data.
    //

    private val fileName = "playlist.json"

    // 파일 읽기/쓰기 담당
    private val fileAccessor = FileAccessor(context, fileName)

    // json 객체로 변환할 때 필요한 type
    private val trackListType = Types.newParameterizedType(List::class.java, Track::class.java)

    //----------------------------------------------------------
    // Public interface.
    //

    // 현재 tracks 데이터를 가지고 있는 list.
    val playlist = mutableListOf<Track>()

    /**
     * playlist 변수를 초기화한다.
     */
    fun initPlaylist() {
        // 파일을 읽어 json 문자열을 가져온다.
        val jsonString = fileAccessor.readData()

        // json 문자열을 파싱해 리스트로 만든다.
        val initData = JsonConverter.jsonToObject<List<Track>>(jsonString, trackListType) ?: emptyList()

        // 만든 리스트를 playlist 변수에 넣는다.
        playlist.addAll(initData)
    }

    /**
     * Track을 리스트에 추가하고 변경 내용을 파일에 저장한다.
     */
    fun addTrack(track: Track) {
        playlist.add(track)
        saveToJsonFile()
    }

    /**
     * Track을 리스트에서 삭제하고 변경 내용을 파일에 저장한다.
     */
    fun removeTrack(position: Int) {
        playlist.removeAt(position)
        saveToJsonFile()
    }

    /**
     * 리스트에서 track을 변경하고 변경 내용을 파일에 저장한다.
     */
    fun updateTrack(position: Int, track: Track) {
        playlist[position] = track
        saveToJsonFile()
    }

    //----------------------------------------------------------
    // Internal support interface.
    //

    /**
     * 현재 playlist 데이터를 파일에 저장한다.
     */
    private fun saveToJsonFile() {
        // 리스트를 json 데이터로 변환
        val jsonString = JsonConverter.listToJson(playlist, trackListType)

        // 변환해 만든 json 데이터를 파일에 저장
        fileAccessor.writeData(jsonString)
    }
}