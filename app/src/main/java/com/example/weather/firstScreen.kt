package com.example.weather

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.UIControllers.MainScreenViewModel
import com.example.weather.view.components.FigureComponent
import com.example.weather.view.components.MainScreenInfoComponent
import kotlinx.coroutines.launch
import java.util.stream.IntStream.range

@Preview(showBackground = true)
@Composable
fun firstScreen (
    onMainInfoClicked: () -> Unit = {},
    onWeeklyForecastClicked: () -> Unit = {},
    onHourlyForecastClicked: () -> Unit = {},
    onSettingsClicked: () -> Unit = {},
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
){

    var showDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(150.dp)
                    .fillMaxHeight(),
                drawerContainerColor = Color.White.copy(alpha = 0.8f) // Set transparency here

            ) {
                Column {
                    Text("Side Menu", modifier = Modifier.padding(2.dp))
                    HorizontalDivider()
                    Text("Cities", modifier = Modifier.padding(2.dp))
                    HorizontalDivider()
                    LazyColumn (modifier = Modifier.height(187.dp).width(150.dp)){
                        items(20) {
                            Text(text = "city", modifier = Modifier.padding(4.dp).clickable {  })
                        }
                    }
                    Text(text = "Add new city", modifier = Modifier.padding(2.dp).clickable {
                        showDialog = true
                    })
                    HorizontalDivider()
                    Row (modifier = Modifier.padding(2.dp).clickable {onSettingsClicked() }){
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
                        Text(text = "Settings")
                    }

                }
            }
        }
    ) {
        if (showDialog) {
            TextInputBox(
                modifier = Modifier.height(200.dp),
                onDismissRequest = { showDialog = false },
                onConfirmRequest = { showDialog = false }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
        ) {
            IconButton(onClick = {
                coroutineScope.launch{drawerState.open()}
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "BackArrow"
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(5f))
            MainScreenInfoComponent(
                city = "Copenhagen", // Provide appropriate city name
                temp = "10",         // Provide appropriate temperature
                R.drawable.hat,
                R.drawable.trunks
            )


            Spacer(modifier = Modifier.weight(5f))
            HourlyForecast(
                onClick = { onHourlyForecastClicked() },
                modifier = Modifier.weight(15f).padding(horizontal = 10.dp)
            )
            Spacer(
                modifier = Modifier.weight(5f)
            )

            Spacer(modifier = Modifier.weight(5f))
            WeeklyForecast(
                onClick = { onWeeklyForecastClicked() },
                modifier = Modifier.weight(15f).padding(horizontal = 10.dp)
            )
            Spacer(
                modifier = Modifier.weight(5f)
            )
        }
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
fun HourlyForecast(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clickable {onClick()}
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
