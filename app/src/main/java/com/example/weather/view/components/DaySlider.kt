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
import com.example.weather.model.DayData
import java.time.ZonedDateTime


@Composable
fun DaySlider(
    data: List<DayData>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(data) { item -> DayBox(data = item)
        }
    }
}

@Preview
@Composable
fun PreviewDaySlider() {
    val dayData = List(8) { // Create a list of 24 hours of data
        DayData(
            date = ZonedDateTime.of(2025, 1, 9, 0, 0, 0, 0, ZonedDateTime.now().zone),
            updatedAt = ZonedDateTime.of(2025, 1, 9, 0, 0, 0, 0, ZonedDateTime.now().zone),
            dayOfWeek = "Thursday",
            maxTempC = 30.0,
            minTempC = 20.0,
            maxHumidity = 80.0,
            minHumidity = 50.0,
            maxUV = 6.0,
            minUV = 1.0,
            maxWindSpeed = 20.0,
            minWindSpeed = 5.0,
            weatherCondition = Condition.CLOUDS
        )
    }
    DaySlider(data = dayData)
}