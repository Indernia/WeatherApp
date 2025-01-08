package com.example.weather.model

import java.time.ZonedDateTime

data class HourData(
    var timestamp: ZonedDateTime,
    var temperature: Double,
    var humidity: Double,
    var windSpeed: Double,
    var condition: Condition,
    var updatedAt: ZonedDateTime,
)
