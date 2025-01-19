package com.example.weather.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.UIControllers.MainScreenViewModel
import com.example.weather.view.components.AddCityComp
import com.example.weather.view.components.ClearBackground
import com.example.weather.view.components.CloudyBackground
import com.example.weather.view.components.DrizzleBackground

import com.example.weather.view.components.SnowBackground
import com.example.weather.view.components.ThunderstormBackground
import com.example.weather.UIControllers.CitySelectorViewModel
import com.example.weather.view.components.CitySelectionContainer
import com.example.weather.view.components.WeatherBackground


@Preview
@Composable
fun CitySelectorScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    handleClickBack: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        val mainViewModel = remember { MainScreenViewModel(context = context) }
        val hourDataList by mainViewModel.hourDataState.collectAsState()
        val dayDataList by mainViewModel.dayDataState.collectAsState()
        val firstDayData = dayDataList.firstOrNull()
        val firstHourData = hourDataList.firstOrNull()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
        ) {
            val weatherCondition = dayDataList.firstOrNull()?.weatherCondition ?: "Clear"

            WeatherBackground(weatherCondition)


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
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(onClick = { handleClickBack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "BackArrow"
                            )
                        }
                    }


                    CitySelectionContainer(
                        modifier = modifier,
                        viewModel = CitySelectorViewModel(context = context)
                    )
                }
            }

        }
    }
}
