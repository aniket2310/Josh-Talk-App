package com.example.joshtalk.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin



@Composable
fun Gauge(
    value: Float,
    min: Float = 0f,
    max: Float = 80f
) {
    val limit = 50f


    val animatedValue by animateFloatAsState(
        targetValue = value.coerceIn(min, max),
        animationSpec = tween(durationMillis = 1000)
    )


    val textMeasurer = rememberTextMeasurer()

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(300.dp)) {
        Canvas(modifier = Modifier.fillMaxSize().padding(20.dp)) {
            val size = this.size
            val center = Offset(size.width / 2, size.height / 2)
            val radius = size.width / 2
            val strokeWidth = 40f


            val startAngle = 135f


            val blueSweep = 120f


            val redSweep = 150f


            fun getAngleForValue(v: Float): Float {
                return if (v <= limit) {

                    startAngle + (v / limit) * blueSweep
                } else {

                    val extra = v - limit
                    val range = max - limit
                    (startAngle + blueSweep) + (extra / range) * redSweep
                }
            }


            drawArc(
                color = Color(0xFF4285F4), // Google Blue
                startAngle = startAngle,
                sweepAngle = blueSweep,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
            )


            drawArc(
                color = Color(0xFFD94526),
                startAngle = startAngle + blueSweep,
                sweepAngle = redSweep,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
            )



            val labelRadius = radius - 55f
            val labels = listOf(10, 20, 30, 40, 50, 60)

            labels.forEach { markValue ->
                val angle = getAngleForValue(markValue.toFloat())
                val rad = angle * (PI / 180f).toFloat()


                val x = center.x + labelRadius * cos(rad)
                val y = center.y + labelRadius * sin(rad)

                val textLayoutResult = textMeasurer.measure(
                    text = markValue.toString(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                )


                val textSize = textLayoutResult.size
                drawText(
                    textLayoutResult,
                    topLeft = Offset(
                        x - textSize.width / 2,
                        y - textSize.height / 2
                    )
                )


                val tickStartRadius = radius - 25f
                val tickEndRadius = radius - 15f
                val tickStart = Offset(
                    center.x + tickStartRadius * cos(rad),
                    center.y + tickStartRadius * sin(rad)
                )
                val tickEnd = Offset(
                    center.x + tickEndRadius * cos(rad),
                    center.y + tickEndRadius * sin(rad)
                )

            }


            val needleAngle = getAngleForValue(animatedValue)

            rotate(degrees = needleAngle + 90f, pivot = center) {

                drawLine(
                    color = Color.LightGray,
                    start = center,
                    end = Offset(center.x, center.y - radius + 40f),
                    strokeWidth = 8f,
                    cap = StrokeCap.Round
                )
            }


            drawCircle(
                color = Color.Gray,
                radius = 15f,
                center = center
            )
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 100.dp)
        ) {
            Text(
                text = "${animatedValue.toInt()}",
                style = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (animatedValue >= limit) Color(0xFFD94526) else Color(0xFF5F6368)
                )
            )
            Text(
                text = "db",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF5F6368)
                )
            )
        }
    }
}