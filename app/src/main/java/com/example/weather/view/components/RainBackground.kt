package com.example.weather.view.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
        RainEffect()
    }
}

@Composable
fun RainEffect() {
    // A state to hold the positions of raindrops
    val raindrops = remember { mutableStateListOf<RainDrop>() }
    val dropCount = 100

    // Generate raindrops
    LaunchedEffect(Unit) {
        while (raindrops.size < dropCount) {
            raindrops.add(
                RainDrop(
                    x = (0..1000).random() / 1000f, // Random X position (0 to 1 as percentage of width)
                    y = -0.1f, // Start above the screen
                    speed = (2..5).random() / 100f // Random speed
                )
            )
            delay(100) // Add new raindrops gradually
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        raindrops.forEach { drop ->
            drawRaindrop(drop)
            drop.y += drop.speed // Update drop's Y position
            if (drop.y > 1f) { // Reset if it falls below the screen
                drop.y = -0.1f
                drop.x = (0..1000).random() / 1000f
                drop.speed = (2..5).random() / 100f
            }
        }
    }
}

fun DrawScope.drawRaindrop(drop: RainDrop) {
    val width = size.width
    val height = size.height
    val startX = drop.x * width
    val startY = drop.y * height
    val endX = startX
    val endY = startY + 20.dp.toPx() // Length of the raindrop
    drawLine(
        color = Color.White.copy(alpha = 0.5f),
        start = androidx.compose.ui.geometry.Offset(startX, startY),
        end = androidx.compose.ui.geometry.Offset(endX, endY),
        strokeWidth = 2f
    )
}

// Data class for raindrop properties
data class RainDrop(var x: Float, var y: Float, var speed: Float)
