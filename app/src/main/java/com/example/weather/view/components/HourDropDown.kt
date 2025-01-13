package com.example.weather.view.components

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
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF87CEEB)),
    ){
    Row(
        modifier = Modifier
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(100.dp),
        ){
            val formattedTimestamp = hour.timestamp.format(DateTimeFormatter.ofPattern("HH:mm"))
            Text(
                text = formattedTimestamp,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        Text(
            text = "${hour.condition}",
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
}
}
@Preview
@Composable
fun hourdropdownpreview() {
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