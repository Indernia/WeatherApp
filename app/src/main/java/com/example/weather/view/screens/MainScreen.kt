package com.example.weather.view.screens


import android.util.Log
import com.example.weather.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.UIControllers.MainScreenViewModel
import com.example.weather.view.components.HourSlider
import com.example.weather.view.components.DaySlider
import com.example.weather.view.components.MainScreenInfoComponent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.view.components.ClearBackground
import com.example.weather.view.components.CloudyBackground
import com.example.weather.view.components.DrizzleBackground

import com.example.weather.view.components.SnowBackground
import com.example.weather.view.components.ThunderstormBackground
import kotlinx.coroutines.flow.first
import com.example.weather.view.components.WeatherBackground



@Preview(showBackground = true)
@Composable
fun MainScreen (
    mainViewModel: MainScreenViewModel = viewModel(),
    city: String = "Copenhagen",
    onMainInfoClicked: () -> Unit = {},
    onSettingsClicked: () -> Unit = {},
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
){
    val dayDataList by mainViewModel.dayDataState.collectAsState()
    val hourDataList by mainViewModel.hourDataState.collectAsState()
    val weatherCondition = dayDataList.firstOrNull()?.weatherCondition ?: "Clear"
    val temperature = dayDataList.firstOrNull()?.tempK?.minus(273.15)?.toInt().toString()

    WeatherBackground(weatherCondition)

    Log.println(Log.DEBUG, "MainScreen", hourDataList.toString())
    Log.println(Log.DEBUG, "MainScreen", dayDataList.toString())

    Box(modifier = Modifier.fillMaxSize()) {

    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier =  Modifier.fillMaxSize()
        ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { onSettingsClicked() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings_24px),
                    contentDescription = "Daily Breakdown"
                )
            }
        }
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .height(500.dp)
                    .wrapContentSize(Alignment.TopCenter)
            ) {
                MainScreenInfoComponent(
                    city = city,
                    temp = temperature,
                    weatherCondition = weatherCondition,
                    onClick = { /* ToDO */ }
                )
            }
            HourSlider(
                data = hourDataList,
                modifier = Modifier.height(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

        Log.println(Log.DEBUG, "MainScreen", dayDataList.toString())
            DaySlider(
                data = dayDataList,
                modifier = Modifier.height(100.dp)
            )

        Spacer(modifier = Modifier.height(20.dp))
        }
}   }