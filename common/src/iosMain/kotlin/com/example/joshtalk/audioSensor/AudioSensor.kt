package com.example.joshtalk.audioSensor


import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.AVFAudio.*
import platform.CoreAudioTypes.kAudioFormatMPEG4AAC
import platform.Foundation.NSError
import platform.Foundation.NSURL
import platform.Foundation.NSTemporaryDirectory
import kotlin.math.log10


actual class AudioSensor {

    private var recorder: AVAudioRecorder? = null

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    actual fun start() {
        val session = AVAudioSession.sharedInstance()

        try {
            session.setCategory(
                category = AVAudioSessionCategoryPlayAndRecord,
                mode = AVAudioSessionModeDefault,
                options = 0u,
                error = null
            )
            session.setActive(true, error = null)
        } catch (e: Exception) {
            println("AudioSession Error: ${e.message}")
        }

        val url = NSURL.fileURLWithPath(NSTemporaryDirectory() + "noise.m4a")


        val settings: Map<Any?, *> = mapOf(
            AVFormatIDKey to kAudioFormatMPEG4AAC,
            AVSampleRateKey to 44100.0,
            AVNumberOfChannelsKey to 1,
            AVEncoderAudioQualityKey to AVAudioQualityHigh
        )

        memScoped {
            val err = alloc<ObjCObjectVar<NSError?>>()

            val rec = AVAudioRecorder(
                url,
                settings = settings,
                error = err.ptr
            )

            if (err.value != null) {
                println("Recorder Error: ${err.value?.localizedDescription}")
                return
            }

            recorder = rec.apply {
                meteringEnabled = true
                prepareToRecord()
                record()
            }
        }
    }

    actual fun getNoiseLevel(): Float {
        val r = recorder ?: return 0f
        r.updateMeters()


        val peakPower = r.peakPowerForChannel(0u)


        val level = peakPower + 160.0f


        return if (level > 0) level else 0f
    }

    actual fun stop() {
        recorder?.stop()
        recorder = null
    }
}