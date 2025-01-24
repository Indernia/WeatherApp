package com.example.weather.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.example.weather.viewmodel.HourlyBreakdownViewModel
import com.example.weather.view.components.CityResume
import com.example.weather.view.components.HourDropDownList

import com.example.weather.view.components.WeatherBackground

@Composable
fun HourlyBreakdownScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    weatherCondition: String
) {
    val context = LocalContext.current
    val hourlyViewModel = remember { HourlyBreakdownViewModel(context = context) }
    val hourDataList by hourlyViewModel.hourDataState.collectAsState()
    val dayDataList by hourlyViewModel.dayDataState.collectAsState()
    val firstDayData = dayDataList.firstOrNull()
    val firstHourData = hourDataList.firstOrNull()
    val city = hourlyViewModel.currentCity.value

    WeatherBackground(weatherCondition)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(WindowInsets.statusBars.asPaddingValues()),
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = city,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                }
                if (firstDayData != null && firstHourData != null) {
                    CityResume(
                        daydata = firstDayData,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {

                }

                HourDropDownList(hours = hourDataList)
            }

        }
    }
}
