package com.example.weather.view.components

import android.icu.text.DecimalFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.weather.R
import com.example.weather.data.HourData
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun HourDropDown(
    hour: HourData,
    modifier: Modifier = Modifier
) {
    // State to manage dropdown expansion
    var expanded by remember { mutableStateOf(false) }

    val decimalFormat = remember { DecimalFormat("#.00") }
    val celsiusTemperature = (hour.temperature - 273.15).roundToInt()
    val uvi = hour.uv.roundToInt()
    val humidity = hour.humidity.roundToInt()
    val windSpeed = hour.windSpeed.roundToInt()

    val formattedTimestamp = remember {
        LocalDateTime.ofInstant(
            Instant.ofEpochSecond(hour.timestamp.toLong()), // Convert Int to Instant
            ZoneId.systemDefault() // Convert to LocalDateTime
        ).format(DateTimeFormatter.ofPattern("HH:mm"))
    }
    val weatherImage = when (hour.condition) {
        "Clear" -> R.drawable.sun
        "Clouds" -> R.drawable.darkcloud
        "Rain" -> R.drawable.rainy
        "Snow" -> R.drawable.snow
        "Thunderstorm" -> R.drawable.thunder
        else -> R.drawable.darkcloud
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { expanded = !expanded }, // Toggle expansion on click
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary), // Use theme color
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column { // Wrap everything in a Column so expanded content aligns properly
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = formattedTimestamp,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold ,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )


                Image(
                    painter = painterResource(id = weatherImage),
                    contentDescription = "${hour.condition} icon",
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Fit
                )


                Row(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$celsiusTemperature°C",
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
                        .background(MaterialTheme.colorScheme.primaryContainer) // Lighter theme color
                ) {
                    Text(
                        text = "${stringResource(R.string.Humidity)}: $humidity%",
                    )
                    Text(
                        text = "${stringResource(R.string.UVI)}: $uvi",
                    )
                    Text(
                        text = "${stringResource(R.string.Windspeed)}: $windSpeed m/s",
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
