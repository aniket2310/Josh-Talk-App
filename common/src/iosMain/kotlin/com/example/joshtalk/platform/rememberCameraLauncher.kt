package com.example.joshtalk.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.interop.LocalUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.usePinned
import kotlinx.cinterop.addressOf
import platform.posix.memcpy
import org.jetbrains.skia.Image
import platform.UIKit.UIImage
import platform.UIKit.UIImagePickerController
import platform.UIKit.UIImagePickerControllerDelegateProtocol
import platform.UIKit.UIImagePickerControllerSourceType
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.UIKit.UIViewController
import platform.darwin.NSObject
import platform.Foundation.NSData
import platform.UIKit.UIImageJPEGRepresentation

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun rememberCameraLauncher(onImageCaptured: (ImageBitmap?) -> Unit): CameraLauncher {
    val viewController = LocalUIViewController.current

    val delegate = remember {
        object : NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {
            override fun imagePickerController(
                picker: UIImagePickerController,
                didFinishPickingMediaWithInfo: Map<Any?, *>
            ) {
                val image = didFinishPickingMediaWithInfo["UIImagePickerControllerOriginalImage"] as? UIImage

                if (image != null) {

                    val data: NSData? = UIImageJPEGRepresentation(image, 0.8)
                    if (data != null) {
                        val bytes = data.bytes
                        val length = data.length.toInt()
                        val byteArray = ByteArray(length)

                        byteArray.usePinned {
                            platform.posix.memcpy(it.addressOf(0), bytes, length.toULong())
                        }

                        try {
                            val skiaImage = Image.makeFromEncoded(byteArray)
                            onImageCaptured(skiaImage.toComposeImageBitmap())
                        } catch (e: Exception) {
                            e.printStackTrace()
                            onImageCaptured(null)
                        }
                    }
                } else {
                    onImageCaptured(null)
                }
                picker.dismissViewControllerAnimated(true, null)
            }

            override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
                picker.dismissViewControllerAnimated(true, null)
            }
        }
    }

    return remember {
        CameraLauncher {
            val picker = UIImagePickerController()
            picker.sourceType = UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera
            picker.delegate = delegate
            viewController.presentViewController(picker, animated = true, completion = null)
        }
    }
}