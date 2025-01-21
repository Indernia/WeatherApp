package com.example.weather.view.components

import android.icu.text.DecimalFormat
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.weather.R
import com.example.weather.model.Condition
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


    @Composable
    fun DayDropDown(
        day: DayData,
        modifier: Modifier = Modifier
    ) {
        // State to manage dropdown expansion
        var expanded by remember { mutableStateOf(false) }

        val decimalFormat = remember { DecimalFormat("#.00") }
        val celsiusTemperature = decimalFormat.format(day.tempK?.minus(273.15))
        val celsiusmax = decimalFormat.format(day.maxTempK?.minus(273.15))
        val celsiusmin = decimalFormat.format(day.minTempK?.minus(273.15))

        val formattedTimestamp = remember {
            LocalDateTime.ofInstant(
                Instant.ofEpochSecond(day.date.toLong()), // Convert Int to Instant
                ZoneId.systemDefault() // Convert to LocalDateTime
            ).format(DateTimeFormatter.ofPattern("EEEE"))
        }
        val weatherImage = when (day.weatherCondition) {
            "Clear" -> R.drawable.cloud
            "Clouds" -> R.drawable.cloud
            "Rain" -> R.drawable.rainy
            "Snow" -> R.drawable.cloud
            "Thunderstorm" -> R.drawable.cloud
            else -> R.drawable.cloud
        }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { expanded = !expanded }, // Toggle expansion on click
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = formattedTimestamp,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )



                    Image(
                        painter = painterResource(id = weatherImage),
                        contentDescription = "${day.weatherCondition} icon",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(top = 30.dp),
                        contentScale = ContentScale.Fit
                    )


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
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text("${stringResource(R.string.Humidity)}: ${day.humidity}%")
                        Text("UV Index: ${day.uvi}")
                        Text("${stringResource(R.string.Windspeed)}: ${day.windSpeed} km/h")
                        Text("Min temp: $celsiusmin°")
                        Text("Max temp: $celsiusmax°")
                    }
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun DayDropDownPreview() {
    val dayData = DayData(
        date = "1736428200",
        location = 1,
        updatedAt = 1736428200,
        maxTempK = 300.0,
        minTempK = 280.0,
        tempK = 290.0,
        humidity = 65.0,
        uvi = 5.5,
        windSpeed = 10.0,
        windGustSpeed = 15.0,
        weatherCondition = "Cloudy"
    )

    DayDropDown(dayData)
}
