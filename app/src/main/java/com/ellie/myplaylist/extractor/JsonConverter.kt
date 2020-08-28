package com.ellie.myplaylist.extractor

import com.squareup.moshi.Moshi
import java.lang.reflect.Type

/**
 * Json 문자열과 Java 객체를 변환해 주는 Singleton Object.
 * moshi 라이브러리를 사용한다.
 */
object JsonConverter {

    //----------------------------------------------------------
    // Instance data.
    //

    private val moshi = Moshi.Builder().build()

    //----------------------------------------------------------
    // Public interface.
    //

    /**
     * Json 문자열을 Java 객체로 변환한다.
     */
    fun <T> jsonToObject(jsonString: String, type: Type): T? {
        return moshi.adapter<T>(type).fromJson(jsonString)
    }

    /**
     * Java 객체를 Json 문자열로 변환한다.
     */
    fun <T> objectToJson(data: T, type: Type): String {
        return moshi.adapter<T>(type).toJson(data)
    }

    /**
     * Java 리스트 객체를 Json 문자열로 변환한다.
     */
    fun <T> listToJson(list: List<T>, type: Type): String {
        return moshi.adapter<List<T>>(type).toJson(list)
    }
}