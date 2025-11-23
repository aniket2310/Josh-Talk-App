package com.example.joshtalk.platform

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap


@Composable
actual fun rememberCameraLauncher(onImageCaptured: (ImageBitmap?) -> Unit): CameraLauncher {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        onImageCaptured(bitmap?.asImageBitmap())
    }

    return remember {
        CameraLauncher {
            launcher.launch()
        }
    }
}