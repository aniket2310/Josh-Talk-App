package com.example.joshtalk.platform


expect class AudioRecorder() {
    fun start(fileName: String)
    fun stop()
}