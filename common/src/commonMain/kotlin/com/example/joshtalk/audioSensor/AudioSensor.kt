package com.example.joshtalk.audioSensor

expect class AudioSensor() {
    fun start()
    fun getNoiseLevel(): Float
    fun stop()

}