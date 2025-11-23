package com.example.joshtalk.platform

import platform.AVFAudio.AVAudioRecorder
import kotlinx.cinterop.*
import platform.AVFAudio.*
import platform.CoreAudioTypes.*
import platform.Foundation.*


actual class AudioRecorder {
    private var recorder: AVAudioRecorder? = null

    @OptIn(ExperimentalForeignApi::class)
    actual fun start(fileName: String) {
        val session = AVAudioSession.sharedInstance()
        try {
            session.setCategory(
                AVAudioSessionCategoryPlayAndRecord,
                mode = AVAudioSessionModeDefault,
                options = 0u,
                error = null
            )
            session.setActive(true, error = null)
        } catch (e: Exception) {
            println("AudioSession setup failed: ${e.message}")
        }

        val url = NSURL.fileURLWithPath(NSTemporaryDirectory() + "$fileName.m4a")

        val settings: Map<Any?, *> = mapOf(
            AVFormatIDKey to kAudioFormatMPEG4AAC,
            AVSampleRateKey to 44100.0,
            AVNumberOfChannelsKey to 1,
            AVEncoderAudioQualityKey to AVAudioQualityHigh
        )

        memScoped {
            val error = alloc<ObjCObjectVar<NSError?>>()
            recorder = AVAudioRecorder(url, settings, error.ptr)

            if (error.value != null) {
                println("Recorder init failed: ${error.value}")
            } else {
                recorder?.prepareToRecord()
                recorder?.record()
            }
        }
    }

    actual fun stop() {
        recorder?.stop()
        recorder = null
    }
}
