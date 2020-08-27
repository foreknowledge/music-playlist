package com.ellie.myplaylist.util

import com.squareup.moshi.Moshi
import java.lang.reflect.Type

object JsonConverter {
    private val moshi = Moshi.Builder().build()

    fun <T> jsonToObject(jsonString: String, type: Type): T? {
        return moshi.adapter<T>(type).fromJson(jsonString)
    }

    fun <T> objectToJson(data: T, type: Type): String {
        return moshi.adapter<T>(type).toJson(data)
    }

    fun <T> listToJson(list: List<T>, type: Type): String {
        return moshi.adapter<List<T>>(type).toJson(list)
    }
}