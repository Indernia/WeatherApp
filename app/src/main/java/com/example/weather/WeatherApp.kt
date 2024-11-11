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

enum class WeatherScreens{
    FirstPage,
    MainInfoPage
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
        }
    }
}
