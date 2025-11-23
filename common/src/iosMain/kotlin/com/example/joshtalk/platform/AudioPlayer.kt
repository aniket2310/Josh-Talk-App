package com.example.joshtalk.platform

import platform.AVFAudio.AVAudioPlayer
import kotlinx.cinterop.*
import platform.AVFAudio.*
import platform.CoreAudioTypes.*
import platform.Foundation.*


actual class AudioPlayer {
    private var player: AVAudioPlayer? = null

    @OptIn(ExperimentalForeignApi::class)
    actual fun play(fileName: String) {
        val url = NSURL.fileURLWithPath(NSTemporaryDirectory() + "$fileName.m4a")

        memScoped {
            val error = alloc<ObjCObjectVar<NSError?>>()
            player = AVAudioPlayer(contentsOfURL = url, error = error.ptr)

            if (error.value != null) {
                println("Player init failed: ${error.value}")
            } else {
                player?.play()
            }
        }
    }

    actual fun stop() {
        player?.stop()
        player = null
    }
}