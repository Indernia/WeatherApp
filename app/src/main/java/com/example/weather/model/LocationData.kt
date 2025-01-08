package com.example.weather.model

import java.time.ZonedDateTime

data class LocationData(
    val days: MutableList<DayData> = mutableListOf(),
    val hours: MutableList<HourData> = mutableListOf(),
    var name: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var updatedAt: ZonedDateTime,
)
