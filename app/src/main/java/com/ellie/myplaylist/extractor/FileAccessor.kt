package com.ellie.myplaylist.extractor

import android.app.Application
import android.content.Context
import android.util.Log
import java.io.*

/**
 * 파일에 접근해 읽기/쓰기를 담당한다.
 * Context와 접근할 File의 이름을 인자로 받는다.
 */
class FileAccessor(private val context: Context, private val fileName: String) {

    //----------------------------------------------------------
    // Public interface.
    //

    /**
     * 파일에 접근해 데이터를 읽어 문자열로 변환해 반환한다.
     */
    fun readData(): String {
        try {
            context.openFileInput(fileName).use { inputStream ->
                // 파일을 열어 Buffered Reader 생성
                val reader = BufferedReader(InputStreamReader(inputStream))

                // reader로 모든 라인을 읽어 ""으로 연결한 문자열 반환
                return reader.readLines().joinToString("")
            }
        } catch (e: FileNotFoundException) {
            // 파일이 없다면 sample data가 들어있는 파일 생성
            writeData(sampleData)

            // sample data 반환
            return sampleData
        } catch (e: IOException) {
            // IO Exception 발생했을 시 로그 남기고 빈 문자열 반환
            Log.e(TAG, "IOException while reading data. ${e.message}")
            return ""
        }
    }

    /**
     * 파일에 접근해 문자열 데이터를 쓴다.
     */
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