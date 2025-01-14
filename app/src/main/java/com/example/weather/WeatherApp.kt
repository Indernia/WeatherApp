package com.example.weather

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
/*
enum class WeatherScreens{
    FirstPage,
    MainInfoPage,
    weeklyForecast,
    DetailedWeekly,
    HourlyForecast,
    Settings
}

@Composable
fun WeatherApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {

    Scaffold(
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = WeatherScreens.FirstPage.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = WeatherScreens.FirstPage.name
            ) {
               firstScreen(
                   onMainInfoClicked = {
                       navController.navigate(WeatherScreens.MainInfoPage.name)
                   },
                   onWeeklyForecastClicked = {
                       navController.navigate(WeatherScreens.weeklyForecast.name)
                   } ,
                   onHourlyForecastClicked = {
                       navController.navigate(WeatherScreens.HourlyForecast.name)
                   },
                   onSettingsClicked = {
                       navController.navigate(WeatherScreens.Settings.name)
                   }
               )
            }
            composable(route = WeatherScreens.MainInfoPage.name){
                MainInfoScreen(
                    modifier = Modifier.fillMaxSize(),
                    handleClickBack = {
                        navController.navigateUp()

                    }
                )
            }
            composable(route = WeatherScreens.HourlyForecast.name){
                HourlyForecast(
                    modifier = Modifier.fillMaxSize(),
                    handleClickBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(route = WeatherScreens.weeklyForecast.name){
                weeklyForecast(
                    modifier = Modifier.fillMaxSize(),
                    handleClickBack = {
                        navController.navigateUp()
                    },
                    onDetailedWeeklyClicked = {
                        navController.navigate(WeatherScreens.DetailedWeekly.name)
                    }

                )
            }
            composable(route = WeatherScreens.DetailedWeekly.name){
                DetailedWeekly(
                    modifier = Modifier.fillMaxSize(),
                    handleClickBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(route = WeatherScreens.Settings.name){
                settings(
                    modifier = Modifier.fillMaxSize(),
                    handleClickBack = {
                        navController.navigateUp()
                    }
                )
            }

        }
    }
}
*/