package com.example.weather.view.components

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HourDropDown(
    hour: HourData,
    modifier: Modifier = Modifier
) {
    // State to manage dropdown expansion
    var expanded by remember { mutableStateOf(false) }

    val decimalFormat = remember { DecimalFormat("#.00") }
    val celsiusTemperature = decimalFormat.format(hour.temperature - 273.15)

    val formattedTimestamp = remember {
        LocalDateTime.ofInstant(
            Instant.ofEpochSecond(hour.timestamp.toLong()), // Convert Int to Instant
            ZoneId.systemDefault() // Convert to LocalDateTime
        ).format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { expanded = !expanded }, // Toggle expansion on click
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer), // Use theme color
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column { // Wrap everything in a Column so expanded content aligns properly
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = formattedTimestamp,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = hour.condition,
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$celsiusTemperature°",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = if (expanded) "▲" else "▼", // Use arrows to indicate expand/collapse
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            // Show additional details when expanded
            if (expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer) // Lighter theme color
                ) {
                    Text(
                        text = "Humidity: ${hour.humidity}%",
                    )
                    Text(
                        text = "UV Index: ${hour.uv}",
                    )
                    Text(
                        text = "Wind Speed: ${hour.windSpeed} km/h",
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HourDropDownPreview() {
    val hourData = HourData(
        timestamp = 1736428200,
        location = 1,
        updatedAt = 1736428200,
        temperature = 25.0,
        humidity = 60.0,
        windSpeed = 15.0,
        uv = 5.0,
        condition = "Rain"
    )
    HourDropDown(hourData)
}
