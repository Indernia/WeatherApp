package com.example.weather.view.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.view.screens.*

enum class AppScreens {
    MainScreen,
    DailyBreakdown,
    HourlyBreakdown,
    CitySelector,
    Settings
}

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    Scaffold(
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.MainScreen.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = AppScreens.MainScreen.name) {
                MainScreen(
                    onDailyBreakdownClicked = {
                        navController.navigate(AppScreens.DailyBreakdown.name)
                    },
                    onHourlyForecastClicked = {
                        navController.navigate(AppScreens.HourlyBreakdown.name)
                    },
                    onSettingsClicked = {
                        navController.navigate(AppScreens.Settings.name)
                    }
                )
            }

            composable(route = AppScreens.DailyBreakdown.name) {
                DailyBreakdownScreen(
                    handleClickBack = {
                        navController.navigateUp()
                    }
                )
            }

            composable(route = AppScreens.HourlyBreakdown.name) {
                HourlyBreakdownScreen(
                    // Add any required arguments or callbacks
                )
            }

            composable(route = AppScreens.CitySelector.name) {
                CitySelectorScreen(
                    // Add any required arguments or callbacks
                )
            }

            composable(route = AppScreens.Settings.name) {
                //SettingsScreen(
                //)
            }

        }
    }
}
