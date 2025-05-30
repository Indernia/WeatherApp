package com.example.weather.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.weather.R
import com.example.weather.data.HourData
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun HourBox(
    data: HourData,
) {
    val celsiusTemperature = (data.temperature - 273.15).roundToInt()

    val formattedTimestamp = remember(data.timestamp) {
        Instant.ofEpochSecond(data.timestamp.toLong())
            .atZone(ZoneId.of("Europe/Copenhagen"))
            .toLocalDateTime()
            .format(DateTimeFormatter.ofPattern("HH:mm"))
    }
    Card(
        modifier = Modifier
            .size(width = 98.dp, height = 120.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = formattedTimestamp,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 8.dp),
                    )
                    /*
                    Text(
                        text = WeatherConditionText(data.condition),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp),
                    )*/

            val weatherImage = when (data.condition) {
                "Clear" -> R.drawable.sun
                "Clouds" -> R.drawable.darkcloud
                "Rain" -> R.drawable.rainy
                "Snow" -> R.drawable.snow
                "Thunderstorm" -> R.drawable.thunder
                else -> R.drawable.darkcloud
            }

            Image(
                painter = painterResource(id = weatherImage),
                contentDescription = "${data.condition} icon",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 30.dp),
                contentScale = ContentScale.Fit
            )

                    Text(
                        text = "$celsiusTemperature°C",
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp),
                    )


        }
    }
}

@Composable
fun WeatherConditionText(condition: String) : String {
    return when (condition) {
        "Thunderstorm" -> stringResource(R.string.Thunderstorm)
        "Drizzle" -> stringResource(R.string.Drizzle)
        "Rain" -> stringResource(R.string.Rain)
        "Snow" -> stringResource(R.string.Snow)
        "Atmosphere" -> stringResource(R.string.Atmosphere)
        "Clear" -> stringResource(R.string.Clear)
        "Clouds" -> stringResource(R.string.Clouds)
        else -> stringResource(R.string.Clear)
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