package com.example.weather.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.weather.data.HourData
import com.example.weather.data.LocationData

@Composable
fun SavedCity(
    hourData: HourData,
    locationData: LocationData,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .size(width = 280.dp, height = 80.dp),
    ) {
        Box(
            modifier = Modifier
                .size(width = 280.dp, height = 80.dp),
            contentAlignment = Alignment.Center // Center the Row within the Box
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalAlignment = Alignment.CenterVertically // Center items vertically in the Row
            ) {
                Text(
                    text = locationData.name,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
                Text(
                    text = "${hourData.temperature}°",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize

                )
                Text(
                    text = hourData.condition,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            }
        }
    }
}

/*
@Preview
@Composable
fun PreviewCityResumeLocationData() {
    val hourData = HourData(
        timestamp = ZonedDateTime.of(2025, 1, 9, 14, 30, 0, 0, ZonedDateTime.now().zone),
        temperature = 25.0,
        humidity = 60.0,
        windSpeed = 15.0,
        updatedAt = ZonedDateTime.of(2025, 1, 9, 14, 30, 0, 0, ZonedDateTime.now().zone),
        uv = 5.0,
        condition = Condition.RAIN
    )

    val locationData = LocationData(
        name = "Lyngby",
        latitude = 55.7704,
        longitude = 12.5116,
        updatedAt = ZonedDateTime.of(2025, 1, 9, 14, 30, 0, 0, ZonedDateTime.now().zone),
        hours = mutableListOf(hourData)
    )

    SavedCity(locationData = locationData, hourData = hourData)
}


 */