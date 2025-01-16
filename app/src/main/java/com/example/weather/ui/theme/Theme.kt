package com.example.weather.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


val sunnyColorScheme = lightColorScheme(
    primary = BluePrimary,
    onPrimary = Color.Black,
    primaryContainer = BluePrimaryContainer,
    onPrimaryContainer = OnBluePrimaryContainer,
    secondary = BlueSecondary,
    onSecondary = Color.Black,
    secondaryContainer = BlueSecondaryContainer,
    onSecondaryContainer = OnBlueSecondaryContainer,
    background = BackgroundColor,
    surface = SurfaceColor,
)

@Composable
fun WeatherTheme(
    content: @Composable () -> Unit
) {


    MaterialTheme(
        colorScheme = sunnyColorScheme,
        typography = Typography,
        content = content
    )
}