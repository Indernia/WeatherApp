package com.example.weather.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.model.HourData
import com.example.weather.model.Condition
import com.example.weather.model.DayData
import java.time.ZonedDateTime


@Composable
fun HourSlider(
    data: List<HourData>,
    modifier: Modifier = Modifier
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(data, key = { it.timestamp }) { item ->
            HourBox(data = item)
        }
    }
}

/*
@Preview
@Composable
fun PreviewHourSlider() {
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

    HourSlider(data = hourData)
}

 */