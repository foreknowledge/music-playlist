package com.ellie.myplaylist.app

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import java.io.*

class FileAccessor(private val context: Context, private val fileName: String) {
    @WorkerThread
    fun readData(): String {
        try {
            context.openFileInput(fileName).use { inputStream ->
                val reader = BufferedReader(InputStreamReader(inputStream))
                return reader.readLines().joinToString("")
            }
        } catch (e: FileNotFoundException) {
            writeData(sampleData)
            return sampleData
        } catch (e: IOException) {
            Log.e(TAG, "IOException while reading data. ${e.message}")
            return ""
        }
    }

    @WorkerThread
    fun writeData(data: String) {
        // 해당 파일 있으면 열고, 없으면 생성한 뒤 data를 쓴다.
        OutputStreamWriter(context.openFileOutput(fileName, Application.MODE_PRIVATE)).use {
            it.write(data)
        }
    }

    companion object {
        private const val TAG = "FileAccessor"
        private val sampleData = """
            [
              {
                "title": "All We Got (feat. Kanye West & Chicago Children\\'s Choir)",
                "artist": "Chance the Rapper",
                "play_time": "03:23"
              },
              {
                "title": "No Problem (feat. Lil Wayne and 2 Chainz)",
                "artist": "Chance the Rapper",
                "play_time": "05:04"
              },
              {
                "title": "Summer Friends (feat. Jeremih and Francis and the Lights)",
                "artist": "Chance the Rapper",
                "play_time": "04:50"
              },
              {
                "title": "D.R.A.M. Sings Special",
                "artist": "Chance the Rapper",
                "play_time": "01:41"
              }
            ]
        """.trimIndent()
    }
}