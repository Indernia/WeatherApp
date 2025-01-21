package com.example.weather.view.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.R
import kotlinx.coroutines.delay
import kotlin.random.Random

@Preview
@Composable
fun CloudyBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2193B0),
                        Color(0xFF6DD5ED)
                    )
                )
            )
    ) {

        val clouds = listOf(
            Cloud(size = 150.dp,
                alignment = Alignment.TopStart,
                rotationDuration = 3000),
            Cloud(size = 100.dp,
                alignment = Alignment.TopStart,
                rotationDuration = 4000),
            Cloud(size = 200.dp,
                alignment = Alignment.TopEnd,
                rotationDuration = 3500),
            Cloud(size = 120.dp,
                alignment = Alignment.TopCenter,
                rotationDuration = 5000)
        )

        clouds
            .forEach { cloud ->
                RotatingCloud(
                    modifier = Modifier
                        .align(cloud.alignment)
                        .size(cloud.size),
                    rotationDuration = cloud.rotationDuration
                )
            }
    }
}

@Composable
fun RotatingCloud(modifier: Modifier = Modifier, rotationDuration: Int = 3000) {

    val infiniteTransition = rememberInfiniteTransition()
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = rotationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource
            (id = R.drawable.cloud),
        contentDescription = "Cloud",
        modifier = modifier.rotate(rotationAngle)
    )
}


data class Cloud(
    val size: androidx.compose.ui.unit.Dp,
    val alignment: Alignment,
    val rotationDuration: Int
)