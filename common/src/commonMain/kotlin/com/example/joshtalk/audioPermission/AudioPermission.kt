package com.example.joshtalk.audioPermission

import androidx.compose.runtime.Composable


expect class AudioPermission(context: Any?) {
    fun request(callback: (Boolean) -> Unit)
    fun isGranted(): Boolean

    @Composable
    fun Bind()
}