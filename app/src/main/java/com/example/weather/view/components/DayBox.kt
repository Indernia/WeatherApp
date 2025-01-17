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
import androidx.compose.runtime.remember
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
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DayBox(
    data: DayData,
    modifier: Modifier = Modifier
) {
    val formattedDate = remember(data.date) {
        Instant.ofEpochSecond(data.date.toLong())
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .format(DateTimeFormatter.ofPattern("EEEE"))
    }
    OutlinedCard(
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .size(width = 90.dp, height = 120.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = formattedDate,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "${data.weatherCondition}",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
        }
    }
}

/*
@Preview
@Composable
fun PreviewHourDayBox() {
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
    DayBox(data = dayData)
}



 */