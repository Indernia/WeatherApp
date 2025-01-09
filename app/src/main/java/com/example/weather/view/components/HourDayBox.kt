package com.example.weather.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import com.example.weather.model.Condition
import com.example.weather.model.LocationData
import java.time.ZonedDateTime

@Composable
fun HourDayBox(
    data: Any,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .size(width = 90.dp, height = 100.dp)
    ) {
        when (data) {
            is HourData -> {
                Text(
                    text = "${data.timestamp}",
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${data.condition}",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }

            is DayData -> {
                Text(
                    text = "${data.date}",
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${data.weatherCondition}",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }

            else -> {
                Text(
                    text = "Unknown data type",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHourDayBoxHourData() {
    val hourData = HourData(
        //uses .now() only available for android 8.0 or higher
        timestamp = ZonedDateTime.now(),
        temperature = 25.0,
        humidity = 60.0,
        windSpeed = 15.0,
        updatedAt = ZonedDateTime.now(),
        uv = 5.0,
        condition = Condition.RAIN
    )
    HourDayBox(data = hourData)
}

@Preview(showBackground = true)
@Composable
fun PreviewHourDayBoxDayData() {
    val dayData = DayData(
        //uses .now() only available for android 8.0 or higher
        date = ZonedDateTime.now(),
        updatedAt = ZonedDateTime.now(),
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


//This is a "test" to see what happens if we try to pass an unknown data class
@Preview(showBackground = true)
@Composable
fun PreviewHourDayBoxDayUnknown() {
    val unknownData = LocationData(
        days = mutableListOf(),
        hours = mutableListOf(),
        name = "Unknown Location",
        latitude = 0.0,
        longitude = 0.0,
        //uses .now() only available for android 8.0 or higher
        updatedAt = ZonedDateTime.now()
    )

    HourDayBox(data = unknownData)
}
