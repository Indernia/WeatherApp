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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.weather.data.DayData
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.example.weather.R

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
    Card(
        modifier = Modifier
            .size(width = 98.dp, height = 120.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = formattedDate,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(8.dp),
                        textAlign = TextAlign.Center,
                    )
            val weatherImage = when (data.weatherCondition) {
                "Clear" -> R.drawable.sun
                "Clouds" -> R.drawable.darkcloud
                "Rain" -> R.drawable.rainy
                "Snow" -> R.drawable.snow
                "Thunderstorm" -> R.drawable.thunder
                else -> R.drawable.darkcloud
            }

            Image(
                painter = painterResource(id = weatherImage),
                contentDescription = "${data.weatherCondition} icon",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 30.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
