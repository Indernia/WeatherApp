package com.example.weather.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.model.DayData
import com.example.weather.model.HourData

@Composable
fun HourDropDownList(
    hours: List<HourData>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(hours) { item -> HourDropDown(hour = item)
        }
    }
}

@Preview
@Composable
fun HourDropDownListPreview() {
val hourData = List(24) { // Create a list of 24 hours of data
    HourData(
        timestamp = 1736428200,
        location = 1,
        updatedAt = 1736428200,
        temperature = 25.0,
        humidity = 60.0,
        windSpeed = 15.0,
        uv = 5.0,
        condition = "Rain"
    )
}
    HourDropDownList(hourData)
}
