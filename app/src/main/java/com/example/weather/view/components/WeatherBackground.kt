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
        "Cloudy" -> CloudyBackground()
        "Snow" -> SnowBackground()
        "Drizzle" -> DrizzleBackground()
        "Rain" -> SnowBackground()
        "Thunderstorm" -> ThunderstormBackground()
        else -> ClearBackground()
    }
}