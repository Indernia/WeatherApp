package com.example.weather.model

import java.time.ZonedDateTime

data class DayData(
    var date: ZonedDateTime,
    var updatedAt: ZonedDateTime,
    var dayOfWeek: String,

    var maxTempC: Double = 0.0,
    var minTempC: Double = 0.0,

    var maxHumidity: Double = 0.0,
    var minHumidity: Double = 0.0,

    var maxUV: Double = 0.0,
    var minUV: Double = 0.0,

    var maxWindSpeed: Double = 0.0,
    var minWindSpeed: Double = 0.0,

    var weatherCondition: Condition,
)
