package com.example.weather.model

import java.time.ZonedDateTime

data class HourData(
    var timestamp: ZonedDateTime,
    var temperature: Double,
    var humidity: Double,
    var windSpeed: Double,
    var updatedAt: ZonedDateTime,
    var uv: Double,
    var condition: Condition,

)
