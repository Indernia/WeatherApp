package com.example.weather.view.components

import androidx.compose.animation.core.*
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
fun ThunderstormBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2F2F2F),
                        Color(0xFF000000)
                    )
                )
            )
    ) {
        RaindropEffect()

        LightningEffect()
    }
}

@Composable
fun RaindropEffect() {
    val raindrops = remember { mutableStateListOf<Raindrop>() }
    val dropCount = 200

    LaunchedEffect(Unit) {
        while (raindrops
                .size < dropCount) {
            raindrops.add(
                Raindrop(
                    x = Random.nextFloat(),
                    y = -0.1f,
                    speed = Random.nextFloat() * 0.01f + 0.02f
                )
            )
            delay(50)
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        raindrops.forEach { drop ->
            drawRaindrop(drop)
            drop.y += drop.speed
            if (drop.y > 1f) {
                drop.y = -0.1f
                drop.x = Random.nextFloat()
            }
        }
    }
}

fun DrawScope.drawRaindrop(raindrop: Raindrop) {
    val width = size.width
    val height = size.height
    val startX = raindrop.x * width
    val startY = raindrop.y * height

    drawLine(
        color = Color.White.copy(alpha = 0.5f),
        start = androidx.compose.ui.geometry.Offset(startX, startY),
        end = androidx.compose.ui.geometry.Offset(startX, startY + 20.dp.toPx()),
        strokeWidth = 2f
    )
}

data class Raindrop(var x: Float, var y: Float, var speed: Float)

@Composable
fun LightningEffect() {

    var isLightningVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(Random.nextLong(2000, 5000))
            isLightningVisible = true
            delay(100)
            isLightningVisible = false
        }
    }

    if (isLightningVisible) {
        Canvas(modifier = Modifier
            .fillMaxSize()) {
            val topHalfScreen =size.height / 2

            drawRect(
                color = Color.White.copy(alpha = 0.7f),
                topLeft = androidx.compose.ui.geometry.Offset(0f, 0f),
                size = androidx.compose.ui.geometry.Size(size.width, topHalfScreen)
            )
        }
    }
}