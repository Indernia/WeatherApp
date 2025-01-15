package com.example.weather.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weather.model.DayData

@Composable
fun DayDropDownList(
    days: List<DayData>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(days) { item -> DayDropDown(day = item)
        }
    }
}