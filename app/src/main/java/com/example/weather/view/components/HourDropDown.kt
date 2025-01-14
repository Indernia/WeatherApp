package com.example.weather.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import com.example.weather.model.Condition
import com.example.weather.model.HourData
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HourDropDown(
    hour: HourData,
    modifier: Modifier = Modifier
) {
    // State to manage dropdown expansion
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { expanded = !expanded }, //  Toggle expansion on click
        colors = CardDefaults.cardColors(containerColor = Color(0xFF87CEEB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column { // Wrap everything in a Column so expanded content aligns properly
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(90.dp)
            ) {
                val formattedTimestamp = hour.timestamp.format(DateTimeFormatter.ofPattern("HH:mm"))
                Text(
                    text = formattedTimestamp,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = hour.condition.name,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "${hour.temperature}°C",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            }

            // Show additional details when expanded
            if (expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .background(Color(0xFFB0E0E6)) // Lighter color for better visibility
                ) {
                    Text("Temperature: ${hour.temperature}°C")
                    Text("Humidity: ${hour.humidity}%")
                    Text("UV Index: ${hour.uv}")
                    Text("Wind Speed: ${hour.windSpeed} km/h")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HourDropDownPreview() {
    val hourData = HourData(
        timestamp = ZonedDateTime.of(2025, 1, 9, 14, 30, 0, 0, ZonedDateTime.now().zone),
        temperature = 25.0,
        humidity = 60.0,
        windSpeed = 15.0,
        updatedAt = ZonedDateTime.of(2025, 1, 9, 14, 30, 0, 0, ZonedDateTime.now().zone),
        uv = 5.0,
        condition = Condition.RAIN
    )
    HourDropDown(hourData)
}
