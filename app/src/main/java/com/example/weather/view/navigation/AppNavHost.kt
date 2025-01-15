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
import com.example.weather.view.components.NavBar

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
        modifier = modifier,
        bottomBar = {
            NavBar(
                onDailyClicked = {
                    navController.navigate(AppScreens.DailyBreakdown.name)
                },
                onSelectorClicked = {
                    navController.navigate(AppScreens.CitySelector.name)
                },
                onHourlyClicked = {
                    navController.navigate(AppScreens.HourlyBreakdown.name)
                }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = AppScreens.MainScreen.name,
            modifier = modifier.padding(it)
        ) {
            composable(route = AppScreens.MainScreen.name) {
                MainScreen()
            }

            composable(route = AppScreens.DailyBreakdown.name) {
                DailyBreakdownScreen(
                    handleClickBack = {
                        navController.navigateUp()
                    }
                )
            }
/*
            composable(route = AppScreens.HourlyBreakdown.name) {
                HourlyBreakdownScreen(
                    handleClickBack = {
                        navController.navigateUp()
                    }
                )
            }
*/
            composable(route = AppScreens.Settings.name) {
                SettingsScreen(
                    selectedOption = "English",
                    onOptionSelected = { /* Handle setting change */ }
                )
            }
        }
    }

}
