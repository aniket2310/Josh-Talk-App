package com.example.joshtalk.audioPermission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.core.content.ContextCompat



actual class AudioPermission actual constructor(context: Any?) {

    private val androidContext = context as? Context


    private var launcher: ActivityResultLauncher<String>? = null


    private var onResultCallback: ((Boolean) -> Unit)? = null

    actual fun isGranted(): Boolean {
        val ctx = androidContext ?: return false
        return ContextCompat.checkSelfPermission(
            ctx,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    actual fun request(callback: (Boolean) -> Unit) {
        if (isGranted()) {
            callback(true)
        } else {

            this.onResultCallback = callback

            launcher?.launch(Manifest.permission.RECORD_AUDIO)
        }
    }


    @Composable
    actual fun Bind() {
        val localLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->

                onResultCallback?.invoke(isGranted)
            }
        )


        SideEffect {
            launcher = localLauncher
        }
    }
}