package com.example.joshtalk.platform

import android.media.MediaPlayer
import java.io.File


actual class AudioPlayer {
    private var player: MediaPlayer? = null

    actual fun play(fileName: String) {
        try {
            stop()

            val cacheDir = System.getProperty("java.io.tmpdir")?.let { File(it) }
            val file = File(cacheDir, "$fileName.mp4")

            if (file.exists()) {
                player = MediaPlayer().apply {
                    setDataSource(file.absolutePath)
                    prepare()
                    start()
                    setOnCompletionListener {
                        it.release()
                        player = null
                    }
                }
            } else {
                println("Audio file not found: ${file.absolutePath}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    actual fun stop() {
        try {
            player?.stop()
            player?.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        player = null
    }
}