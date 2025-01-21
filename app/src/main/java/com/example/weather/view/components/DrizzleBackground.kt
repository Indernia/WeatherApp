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
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun DrizzleBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6D8EA0),
                        Color(0xFFB0BEC5)
                    )
                )
            )
    ) {
        Drizzle()
    }
}

@Composable
fun Drizzle() {
    val drizzleDrops = remember { mutableStateListOf<DrizzleDrop>() }
    val drizzleCount = 100

    LaunchedEffect(Unit) {
        while (true) {
            drizzleDrops.add(
                DrizzleDrop(
                    x = Random.nextFloat(),
                    y = -0.1f,
                    speed = Random.nextFloat() * 0.01f + 0.005f,
                    size = Random.nextFloat() * 1.5f + 1f
                )
            )
            delay(50)
        }
    }

    Canvas(modifier = Modifier
        .fillMaxSize()) { drizzleDrops
            .forEach { drop ->
            drawDrizzleDrop(drop)
            drop.y += drop.speed

            if (drop.y > 1f) {
                drop.y = -0.1f
                drop.x = Random.nextFloat()
            }
        }
    }
}

fun DrawScope.drawDrizzleDrop(drizzleDrop: DrizzleDrop) {
    val width = size.width
    val height = size.height
    val startX = drizzleDrop.x * width
    val startY = drizzleDrop.y * height

    drawLine(
        color = Color.LightGray.copy(alpha = 0.6f),
        start = androidx.compose.ui.geometry.Offset(startX, startY),
        end = androidx.compose.ui.geometry.Offset(startX, startY + drizzleDrop.size.dp.toPx()),
        strokeWidth = 1.5f
    )
}

data class DrizzleDrop(var x: Float, var y: Float, var speed: Float, var size: Float)