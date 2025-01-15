package com.example.weather.view.screens


import com.example.weather.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale


@Preview(showBackground = true)
@Composable
fun MainScreen (
    temperature: String = "10",
    city: String = "Copenhagen",
    onMainInfoClicked: () -> Unit = {},
    onSettingsClicked: () -> Unit = {},
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
){
    val context = LocalContext.current
    val mainViewModel = MainScreenViewModel(context = context)
    val dayDataList by mainViewModel.dayDataState.collectAsState()
    val hourDataList by mainViewModel.hourDataState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
        ) {
            IconButton(onClick = { onSettingsClicked() }) {
                Icon(
                    painter = painterResource(id = R.drawable.settings_24px),
                    contentDescription = "Daily Breakdown"
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .height(490.dp)
                    .padding(top = 10.dp)
                    .padding(bottom = 10.dp)
            ) {
                MainScreenInfoComponent(
                    city = city,
                    temp = temperature,
                    R.drawable.hat,
                    R.drawable.trunks,
                    onClick = { /* ToDO */ }
                )
            }

            HourSlider(
                data = hourDataList,
                modifier = Modifier.height(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            DaySlider(
                data = dayDataList,
                modifier = Modifier.height(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}