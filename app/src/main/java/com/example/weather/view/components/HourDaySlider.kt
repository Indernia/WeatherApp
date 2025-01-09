package com.example.weather.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.model.HourData
import com.example.weather.model.Condition
import java.time.ZonedDateTime
import androidx.compose.material3.Text
import com.example.weather.model.DayData


@Composable
fun HourDaySlider(
    data: List<Any>,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(data) { item ->
            when (item) {
                is HourData -> {
                    HourDayBox(data = item)
                }
                is DayData -> {
                    HourDayBox(data = item)
                }
                else -> {
                    // In case an unknown dataclass is given
                    Text("Unknown data type")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHourDaySliderHourData() {
    val hourData = List(24) { // Create a list of 24 hours of data (representing a full day)
        HourData(
            timestamp = ZonedDateTime.of(2025, 1, 9, 0, 0, 0, 0, ZonedDateTime.now().zone).plusHours(it.toLong()), // Incrementing the time for each hour
            temperature = 25.0 + it, // Example temperature increase per hour
            humidity = 60.0 + it,    // Example humidity increase per hour
            windSpeed = 15.0 + it,   // Example wind speed increase per hour
            updatedAt = ZonedDateTime.now(),
            uv = 5.0 + it,           // Example UV index increase per hour
            condition = Condition.values()[it % Condition.values().size] // Cycle through conditions
        )
    }

    HourDaySlider(data = hourData)
}

@Preview
@Composable
fun PreviewHourDaySliderDayData() {
    // Create a list of 8 mock DayData objects
    val dayData = List(8) {
        DayData(
            date = ZonedDateTime.now().plusDays(it.toLong()), // Simulating 8 days ahead
            updatedAt = ZonedDateTime.now(),
            dayOfWeek = "Day ${it + 1}",
            maxTempC = 25.0 + it,  // Example temp variation
            minTempC = 15.0 + it,  // Example temp variation
            maxHumidity = 80.0 - it,  // Example humidity variation
            minHumidity = 60.0 - it,  // Example humidity variation
            maxUV = 8.0 - it,  // Example UV variation
            minUV = 1.0 + it,  // Example UV variation
            maxWindSpeed = 20.0 + it,  // Example wind speed variation
            minWindSpeed = 10.0 + it,  // Example wind speed variation
            weatherCondition = Condition.values().getOrElse(it % Condition.values().size) { Condition.CLEAR }
        )
    }

    HourDaySlider(data = dayData)
}
