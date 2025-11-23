package com.example.joshtalk.platform

import android.media.MediaPlayer
import android.media.MediaRecorder
import java.io.File

actual class AudioRecorder {
    private var recorder: MediaRecorder? = null
    private var currentFile: File? = null

    actual fun start(fileName: String) {
        try {

            stop()


            val cacheDir = File(System.getProperty("java.io.tmpdir"))
            if (!cacheDir.exists()) cacheDir.mkdirs()


            currentFile = File(cacheDir, "$fileName.mp4")


            recorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setOutputFile(currentFile!!.absolutePath)
                prepare()
                start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    actual fun stop() {
        try {
            recorder?.stop()
            recorder?.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        recorder = null
    }
}
