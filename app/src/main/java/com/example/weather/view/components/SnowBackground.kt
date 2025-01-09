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
fun SnowBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF87CEEB),
                        Color(0xFFFFFFFF)
                    )
                )
            )
    ) {
        SnowEffect()
    }
}

@Composable
fun SnowEffect() {
    val snowflakes = remember { mutableStateListOf<Snowflake>() }
    val flakeCount = 150

    // Generate snowflakes
    LaunchedEffect(Unit) {
        while (snowflakes.size < flakeCount) {
            snowflakes.add(
                Snowflake(
                    x = (0..1000).random() / 1000f, // Random X position
                    y = -0.1f, // Snow starts above the screen
                    size = (3..8).random().toFloat(), // Random size for snowflake
                    speed = (3..7).random() / 100f // Random falling speed
                )
            )
            delay(50) // Add new snowflakes gradually
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        snowflakes.forEach { flake ->
            drawSnowflake(flake)
            flake.y += flake.speed // Update Y position
            flake.x += (0..2).random() / 1000f - 0.001f // Slight horizontal drift
            if (flake.y > 1f) {
                flake.y = -0.1f
                flake.x = (0..1000).random() / 1000f
                flake.speed = (1..3).random() / 100f
                flake.size = (3..8).random().toFloat()
            }
        }
    }
}

fun DrawScope.drawSnowflake(flake: Snowflake) {
    val width = size.width
    val height = size.height
    val centerX = flake.x * width
    val centerY = flake.y * height
    drawCircle(
        color = Color.White.copy(alpha = 0.8f),
        radius = flake.size.dp.toPx(),
        center = androidx.compose.ui.geometry.Offset(centerX, centerY)
    )
}

data class Snowflake(var x: Float, var y: Float, var size: Float, var speed: Float)
