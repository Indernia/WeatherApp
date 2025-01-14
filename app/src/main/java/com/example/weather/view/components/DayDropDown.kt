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

data class Day(
    val dayname: String,
    val description: String,
    val temperature: Int

)

val Days = arrayOf(
    Day("Day 1", "Sunny",2),
    Day("Day 2", "Rainy",2),
    Day("Day 3", "Cloudy",2),
    Day("Day 4", "Stormy",2),
    Day("Day 5", "Windy",2),
    Day("Day 6", "Snowy",2),
    Day("Day 7", "Foggy",2),
    Day("Day 8", "Thunderstorm",2),
    Day("Day 9", "Sunny",2),
    Day("Day 10", "Rainy",2)
)

@Composable
fun HighlightedDay(day: Day) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF87CEEB))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically, // Align items vertically
            horizontalArrangement = Arrangement.SpaceBetween // Spread items across the row
        ) {
            Column {
                Text(
                    text = day.dayname,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Text(
                text = day.description,
                fontSize = 24.sp,
                color = Color.White
            )
            Text(
                text = "${day.temperature}°C",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Yellow
            )
        }
    }
}

@Composable
fun DayItem(day: Day, index: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF87CEEB)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically, // Align items vertically
            horizontalArrangement = Arrangement.SpaceBetween // Spread items across the row
        ){
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "${day.dayname}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = day.description,
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "${day.temperature}°C",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ScrollableDayList(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Show today's weather in a big box
        HighlightedDay(Days[0])

        // Show the rest of the days
        for (index in 1 until Days.size) {
            DayItem(day = Days[index], index = index)
        }
    }
}
