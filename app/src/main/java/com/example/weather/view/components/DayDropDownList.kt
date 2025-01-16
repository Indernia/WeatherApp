package com.example.weather.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.model.DayData

@Composable
fun DayDropDownList(
    days: List<DayData>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(days) { item -> DayDropDown(day = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DayDropDownListPreview() {
    val dayData = List(7) { // Generate 7 days of data
        DayData(
            date = "1736428200",
            location = 1,
            updatedAt = 1736428200,
            maxTempK = 300.0,
            minTempK = 280.0,
            tempK = 290.0,
            humidity = 65.0,
            uvi = 5.5,
            windSpeed = 10.0,
            windGustSpeed = 15.0,
            weatherCondition = "Cloudy"
        )
    }

    DayDropDownList(dayData)
}