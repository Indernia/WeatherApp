package com.example.weather.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.model.HourData
import com.example.weather.model.Condition
import java.time.ZonedDateTime
import com.example.weather.model.DayData
import com.example.weather.model.LocationData


@Composable
fun HourDaySlider(
    data: List<Any>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(data) { item -> HourDayBox(data = item)
        }
    }
}

@Preview
@Composable
fun PreviewHourDaySliderHourData() {
    val hourData = List(24) { // Create a list of 24 hours of data
        HourData(
            timestamp = ZonedDateTime.of(2025, 1, 9, 0, 0, 0, 0, ZonedDateTime.now().zone).plusHours(it.toLong()), // Incrementing the time for each hour
            temperature = 25.0 + it, // Example temperature increase per hour
            humidity = 60.0,
            windSpeed = 15.0 ,
            updatedAt = ZonedDateTime.now(),
            uv = 5.0 ,
            condition = Condition.RAIN
        )
    }

    HourDaySlider(data = hourData)
}

@Preview
@Composable
fun PreviewHourDaySliderDayData() {
    // Create a list of 8 DayData objects
    val dayData = List(8) {
        DayData(
            date = ZonedDateTime.now().plusDays(it.toLong()), // Simulating 8 days ahead
            updatedAt = ZonedDateTime.now(),
            dayOfWeek = "Day ${it + 1}",
            maxTempC = 25.0,
            minTempC = 15.0,
            maxHumidity = 80.0 ,
            minHumidity = 60.0 ,
            maxUV = 8.0 ,
            minUV = 1.0 ,
            maxWindSpeed = 20.0,
            minWindSpeed = 10.0,
            weatherCondition = Condition.SNOW
        )
    }

    HourDaySlider(data = dayData)
}
@Preview
@Composable
fun PreviewHourDayBoxSliderUnknown() {
    val unknownData = List(10) {
        LocationData(
        days = mutableListOf(),
        hours = mutableListOf(),
        name = "Unknown Location",
        latitude = 0.0,
        longitude = 0.0,
        updatedAt = ZonedDateTime.of(2025, 1, 9, 0, 0, 0, 0, ZonedDateTime.now().zone)
        )
    }
    HourDayBox(data = unknownData)
}