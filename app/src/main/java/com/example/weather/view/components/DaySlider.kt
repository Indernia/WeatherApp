package com.example.weather.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.data.DayData


@Composable
fun DaySlider(
    data: List<DayData>,
    modifier: Modifier = Modifier
) {
    val limitedDayData = data.take(4)
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(data, key = { it.date }) { item ->
            DayBox(data = item)
        }
    }
}
