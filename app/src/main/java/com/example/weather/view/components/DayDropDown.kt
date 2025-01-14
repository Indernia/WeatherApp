package com.example.weather.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material3.MenuDefaults
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
import androidx.compose.ui.unit.Dp
import com.example.weather.model.Condition
import com.example.weather.model.DayData
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@Composable
fun DayDropDown(
    day: DayData,
    modifier: Modifier = Modifier
) {
    // State to manage dropdown expansion
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { expanded = !expanded },  // Toggle expanded state on click
        colors = CardDefaults.cardColors(containerColor = Color(0xFF87CEEB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {

            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(90.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val formattedTimestamp = day.date.format(DateTimeFormatter.ofPattern("EEEE"))
                Text(
                    text = formattedTimestamp,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = day.weatherCondition.name,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "${day.maxTempC}°C",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            }

            // Show additional details if expanded
            if (expanded) {
                // Additional details can be included here
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .background(Color(0xFFB0E0E6)) // Lighter color for additional info
                ) {
                    Text("max Temp: ${day.maxTempC}°C")
                    Text("min Temp: ${day.minTempC}°C")
                    Text("max Humidity: ${day.maxHumidity}")
                    Text("min Humidity: ${day.minHumidity}")
                    Text("max UV: ${day.maxUV}")
                    Text("min UV: ${day.minUV}")
                    Text( "max wind Speed: ${day.maxWindSpeed}")
                    Text( "min wind Speed: ${day.minWindSpeed}")

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DayDropDownPreview() {
    val day = DayData(
        date = ZonedDateTime.of(2025, 1, 9, 14, 30, 0, 0, ZonedDateTime.now().zone),
        maxTempC = 25.0,
        minTempC = 12.0,
        maxHumidity = 2.0,
        minHumidity= 2.0,
        maxUV = 2.0,
        minUV = 2.0,
        maxWindSpeed = 2.0,
        minWindSpeed= 2.0,
        weatherCondition = Condition.RAIN,
        updatedAt = ZonedDateTime.of(2025, 1, 9, 14, 0, 0, 0, ZonedDateTime.now().zone),
        dayOfWeek = "Monday"
    )
    DayDropDown(day)
}
