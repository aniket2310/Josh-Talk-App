package com.example.joshtalk.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap



class CameraLauncher(
    val launch: () -> Unit
)

@Composable
expect fun rememberCameraLauncher(onImageCaptured: (ImageBitmap?) -> Unit): CameraLauncher