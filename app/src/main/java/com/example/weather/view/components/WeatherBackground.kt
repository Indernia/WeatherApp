package com.example.weather.view.components

import androidx.compose.runtime.Composable
import com.example.weather.view.components.ClearBackground
import com.example.weather.view.components.CloudyBackground
import com.example.weather.view.components.DrizzleBackground
import com.example.weather.view.components.SnowBackground
import com.example.weather.view.components.ThunderstormBackground

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