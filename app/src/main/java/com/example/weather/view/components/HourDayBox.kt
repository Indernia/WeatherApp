package com.example.weather.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import com.example.weather.model.Condition
import com.example.weather.model.LocationData
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HourDayBox(
    data: Any,
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .size(width = 90.dp, height = 120.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            //checks the dataclass and makes a box depending on dataclass
            when (data) {
                //makes a box for HourData given dataclass HourData
                is HourData -> {
                    val formattedTimestamp = data.timestamp.format(DateTimeFormatter.ofPattern("HH:mm"))
                    Text(
                        text = formattedTimestamp,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 8.dp),
                    )
                    Text(
                        text = "${data.condition}",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp),
                    )
                    Text(
                        text = "${data.temperature}°C",
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp),
                    )
                }
                //makes a box for DayData given dataclass DayData
                is DayData -> {
                    Text(
                        text = data.dayOfWeek,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 8.dp),
                    )
                    Text(
                        text = "${data.weatherCondition}",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp),
                    )
                }

                else -> {
                    Text(
                        text = "Unknown data type",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHourDayBoxHourData() {
    val hourData = HourData(
        timestamp = ZonedDateTime.of(2025, 1, 9, 14, 30, 0, 0, ZonedDateTime.now().zone),
        temperature = 25.0,
        humidity = 60.0,
        windSpeed = 15.0,
        updatedAt = ZonedDateTime.of(2025, 1, 9, 14, 30, 0, 0, ZonedDateTime.now().zone),
        uv = 5.0,
        condition = Condition.RAIN
    )
    HourDayBox(data = hourData)
}

@Preview
@Composable
fun PreviewHourDayBoxDayData() {
    val dayData = DayData(
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
    HourDayBox(data = dayData)
}

// This is a "test" to see what happens if we try to pass an unknown data class
@Preview
@Composable
fun PreviewHourDayBoxDayUnknown() {
    val unknownData = LocationData(
        days = mutableListOf(),
        hours = mutableListOf(),
        name = "Unknown Location",
        latitude = 0.0,
        longitude = 0.0,
        // Using a fixed timestamp for faster preview
        updatedAt = ZonedDateTime.of(2025, 1, 9, 0, 0, 0, 0, ZonedDateTime.now().zone)
    )

    HourDayBox(data = unknownData)
}
