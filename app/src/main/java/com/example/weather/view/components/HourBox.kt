package com.example.weather.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import com.example.weather.model.Condition
import com.example.weather.model.LocationData
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HourBox(
    data: HourData,
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
                    val formattedTimestamp = data.timestamp
                    Text(
                        text = formattedTimestamp.toString(),
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
    }
}

// commented out the testing previews for new before re making them for a new data model version
/*
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
*/