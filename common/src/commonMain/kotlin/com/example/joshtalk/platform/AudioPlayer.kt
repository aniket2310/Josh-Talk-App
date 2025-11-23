package com.example.joshtalk.platform

expect class AudioPlayer() {
    fun play(fileName: String)
    fun stop()
}