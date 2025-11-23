package com.example.joshtalk.audioPermission

import androidx.compose.runtime.Composable


actual class AudioPermission actual constructor(context: Any?) {

    actual fun isGranted(): Boolean = true

    actual fun request(callback: (Boolean) -> Unit) {
        callback(true)
    }

    @Composable
    actual fun Bind() {

    }
} 
