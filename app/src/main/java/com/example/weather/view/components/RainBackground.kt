package com.example.weather.view.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun RainBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1B3686),
                        Color(0xFF333D57)
                    )
                )
            )
    ) {
        Rain()
    }
}

@Composable
fun Rain() {
    val raindrops = remember { mutableStateListOf<RaindropRain>() }
    val dropCount = 100

    LaunchedEffect(Unit) {
        while (true) {
            if (raindrops.size < dropCount) {
                raindrops.add(
                    RaindropRain(
                        x = (0..1000).random() / 1000f,
                        y = -0.1f,
                        speed = (3..7).random() / 100f
                    )
                )
            } else {
                raindrops.removeAt(0)
            }
            delay(50)
        }
    }

    Canvas(modifier = Modifier
        .fillMaxSize()) {
        raindrops.forEach { raindrop ->
            drawRaindrop(raindrop)
            raindrop.y += raindrop.speed

            if (raindrop.y > 1f) {
                raindrop.y = -0.1f
                raindrop.x = (0..1000).random() / 1000f
                raindrop.speed = (2..5).random() / 100f
            }
        }
    }
}

private fun DrawScope.drawRaindrop(drop: RaindropRain) {
    val width = size.width
    val height = size.height
    val startX = drop.x * width
    val startY = drop.y * height
    val endX = startX
    val endY = startY + 20.dp.toPx()

    drawLine(
        color = Color.White.copy(alpha = 0.5f),
        start = Offset(startX, startY),
        end = Offset(endX, endY),
        strokeWidth = 2f
    )
}


private data class RaindropRain(var x: Float, var y: Float, var speed: Float)