package com.example.weather

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun firstScreen (
    onMainInfoClicked: () -> Unit = {},
    onWeeklyForecastClicked: () -> Unit = {}
){
    Spacer(modifier = Modifier.height(16.dp))
    Row (
        horizontalArrangement = Arrangement.Start,
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "BackArrow"
            )
        }
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ){
        Spacer(modifier = Modifier.weight(5f))
        MainInformation(
            onClick = {onMainInfoClicked()},
            modifier = Modifier.weight(50f).padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.weight(5f))
        HourlyForecast(modifier = Modifier.weight(15f).padding(horizontal = 10.dp))
        Spacer(modifier = Modifier.weight(5f))
        WeeklyForecast(
            onClick = {onWeeklyForecastClicked()},
            modifier = Modifier.weight(15f).padding(horizontal = 10.dp))
        Spacer(modifier = Modifier.weight(5f))
    }
}

@Preview(showBackground = true)
@Composable
fun MainInformation(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clickable {onClick()}
            .fillMaxSize().background(Color.Gray).padding(16.dp)
        ,
        contentAlignment = Alignment.Center
    ){
        Text(text = "Main information view large")
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyForecast(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize().background(Color.Gray).padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Row {
            Box(
                modifier = Modifier.width(40.dp).height(75.dp).background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier.width(40.dp).height(75.dp).background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier.width(40.dp).height(75.dp).background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier.width(40.dp).height(75.dp).background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier.width(40.dp).height(75.dp).background(Color.LightGray)
            )
        }
        Text(
            text = "Hourly forecast",
            fontSize = 25.sp,
        )
    }
}

@Composable
fun WeeklyForecast(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clickable {onClick()}
            .fillMaxSize().background(Color.Gray).padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Weekly Forecast")
    }
}
