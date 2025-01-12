package com.example.weather.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Preview(showBackground = true)
@Composable
fun DateTimeDisplay() {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("EEEE - MMMM d - HH:mm")
    val formattedDateTime = currentDateTime.format(formatter)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = formattedDateTime)
    }
}