package com.example.joshtalk.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object AppIcons {


    val Mic: ImageVector
        get() {
            if (_mic != null) return _mic!!
            _mic = materialIcon(name = "Filled.Mic") {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(12.0f, 14.0f)
                    curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f)
                    verticalLineTo(5.0f)
                    curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f)
                    reflectiveCurveTo(9.0f, 3.34f, 9.0f, 5.0f)
                    verticalLineToRelative(6.0f)
                    curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f)
                    close()
                    moveTo(17.0f, 11.0f)
                    curveToRelative(0.0f, 2.76f, -2.24f, 5.0f, -5.0f, 5.0f)
                    reflectiveCurveToRelative(-5.0f, -2.24f, -5.0f, -5.0f)
                    horizontalLineTo(5.0f)
                    curveToRelative(0.0f, 3.53f, 2.61f, 6.43f, 6.0f, 6.92f)
                    verticalLineTo(21.0f)
                    horizontalLineToRelative(2.0f)
                    verticalLineToRelative(-3.08f)
                    curveToRelative(3.39f, -0.49f, 6.0f, -3.39f, 6.0f, -6.92f)
                    horizontalLineToRelative(-2.0f)
                    close()
                }
            }
            return _mic!!
        }
    private var _mic: ImageVector? = null


    val CameraAlt: ImageVector
        get() {
            if (_cameraAlt != null) return _cameraAlt!!
            _cameraAlt = materialIcon(name = "Filled.CameraAlt") {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(9.0f, 2.0f)
                    lineTo(7.17f, 4.0f)
                    horizontalLineTo(4.0f)
                    curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
                    verticalLineToRelative(12.0f)
                    curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                    horizontalLineToRelative(16.0f)
                    curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                    verticalLineTo(6.0f)
                    curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                    horizontalLineToRelative(-3.17f)
                    lineTo(15.0f, 2.0f)
                    horizontalLineTo(9.0f)
                    close()
                    moveTo(12.0f, 17.0f)
                    curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f)
                    reflectiveCurveToRelative(2.24f, -5.0f, 5.0f, -5.0f)
                    reflectiveCurveToRelative(5.0f, 2.24f, 5.0f, 5.0f)
                    reflectiveCurveToRelative(-2.24f, 5.0f, -5.0f, 5.0f)
                    close()
                    moveTo(12.0f, 9.0f)
                    curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f)
                    reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f)
                    reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f)
                    reflectiveCurveToRelative(-1.34f, -3.0f, -3.0f, -3.0f)
                    close()
                }
            }
            return _cameraAlt!!
        }
    private var _cameraAlt: ImageVector? = null


    val Image: ImageVector
        get() {
            if (_image != null) return _image!!
            _image = materialIcon(name = "Filled.Image") {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(21.0f, 19.0f)
                    verticalLineTo(5.0f)
                    curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                    horizontalLineTo(5.0f)
                    curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
                    verticalLineToRelative(14.0f)
                    curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                    horizontalLineToRelative(14.0f)
                    curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                    close()
                    moveTo(8.5f, 13.5f)
                    lineToRelative(2.5f, 3.01f)
                    lineTo(14.5f, 12.0f)
                    lineToRelative(4.5f, 6.0f)
                    horizontalLineTo(5.0f)
                    lineToRelative(3.5f, -4.5f)
                    close()
                }
            }
            return _image!!
        }
    private var _image: ImageVector? = null


    val Stop: ImageVector
        get() {
            if (_stop != null) return _stop!!
            _stop = materialIcon(name = "Filled.Stop") {
                path(fill = SolidColor(Color.Black)) {
                    moveTo(6.0f, 6.0f)
                    horizontalLineToRelative(12.0f)
                    verticalLineToRelative(12.0f)
                    horizontalLineTo(6.0f)
                    close()
                }
            }
            return _stop!!
        }
    private var _stop: ImageVector? = null
}


fun materialIcon(
    name: String,
    block: ImageVector.Builder.() -> Unit
): ImageVector {
    return ImageVector.Builder(
        name = name,
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24.0f,
        viewportHeight = 24.0f
    ).apply(block).build()
}