package com.example.joshtalk.audioSensor

import android.media.MediaRecorder
import android.util.Log
import java.io.File
import java.io.IOException
import kotlin.math.log10


actual class AudioSensor {
    private var recorder: MediaRecorder? = null
    private var tempFile: File? = null
    private var isRecording = false

    actual fun start() {
        if (isRecording) return

        try {

            tempFile = File.createTempFile("audio_test", ".3gp")

            recorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)


                setOutputFile(tempFile?.absolutePath)

                prepare()
                start()
            }


            recorder?.maxAmplitude

            isRecording = true
            Log.d("AudioSensor", "Microphone started using file: ${tempFile?.absolutePath}")

        } catch (e: Exception) {
            Log.e("AudioSensor", "FAILED to start microphone", e)
            isRecording = false
            recorder = null
        }
    }

    actual fun getNoiseLevel(): Float {
        if (!isRecording || recorder == null) return 0f

        return try {
            val amplitude = recorder?.maxAmplitude ?: 0
            if (amplitude > 0) {

                20 * log10(amplitude.toDouble()).toFloat()
            } else {
                0f
            }
        } catch (e: Exception) {
            0f
        }
    }

    actual fun stop() {
        try {
            recorder?.stop()
            recorder?.release()
        } catch (e: Exception) {

        }


        try {
            tempFile?.delete()
        } catch (e: Exception) {
            Log.e("AudioSensor", "Failed to delete temp file", e)
        }

        recorder = null
        tempFile = null
        isRecording = false
    }
}