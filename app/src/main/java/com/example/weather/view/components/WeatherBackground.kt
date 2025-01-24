package com.example.weather.view.components

import androidx.compose.runtime.Composable


@Composable
fun WeatherBackground(weatherCondition: String) {
    when (weatherCondition) {
        "Clouds" -> CloudyBackground()
        "Snow" -> SnowBackground()
        "Drizzle" -> DrizzleBackground()
        "Rain" -> RainBackground()
        "Thunderstorm" -> ThunderstormBackground()
        else -> ClearBackground()
    }
}