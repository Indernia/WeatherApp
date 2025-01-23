package com.example.weather.view.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.view.screens.*
import com.example.weather.view.components.NavBar
import com.example.weather.viewmodel.NavViewModel
import com.example.weather.viewmodel.SettingsViewModel


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
    modifier: Modifier = Modifier,
    viewModel: NavViewModel = viewModel(),
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val context = LocalContext.current
    val selectedItem = viewModel.selectedItem.value
    val savedLanguage = settingsViewModel.getLanguagePreference(context)

    /*
    Commenting out as the only way this returns true is if the database breaks which we will not handle in this project
    if(WeatherRepository().getCurrentCity(context).equals("")) {
        WeatherRepository.currentCity = "Copenhagen"
        CitySelectorViewModel(LocalContext.current).updateCurrentLocation(1, context = context)
    }
    */


    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .consumeWindowInsets(WindowInsets.systemBars),
        bottomBar = {
            NavBar(
                selectedItem = selectedItem, // Pass selected item state to NavBar
                onDailyClicked = {
                    viewModel.onNavItemSelected(2) // Update selected item in ViewModel
                    navController.navigate(AppScreens.DailyBreakdown.name){
                        launchSingleTop = true
                        popUpTo(AppScreens.MainScreen.name) { inclusive = false }
                    }
                },
                onMainClicked = {
                    viewModel.onNavItemSelected(0) // Update selected item in ViewModel
                    navController.navigate(AppScreens.MainScreen.name){
                        launchSingleTop = true
                        popUpTo(AppScreens.MainScreen.name) { inclusive = false }
                    }
                },
                onHourlyClicked = {
                    viewModel.onNavItemSelected(1) // Update selected item in ViewModel
                    navController.navigate(AppScreens.HourlyBreakdown.name){
                        launchSingleTop = true
                        popUpTo(AppScreens.MainScreen.name) { inclusive = false }
                    }
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
                MainScreen(
                    onSettingsClicked = {navController.navigate(AppScreens.Settings.name)},
                    onSelectorClicked = {navController.navigate(AppScreens.CitySelector.name)}
                )
            }

            composable(route = AppScreens.CitySelector.name) {
                CitySelectorScreen(
                    handleClickBack = {
                        navController.navigateUp()
                    },
                    navController = navController
                )
            }

            composable(route = AppScreens.DailyBreakdown.name) {
                DailyBreakdownScreen(
                )
            }

            composable(route = AppScreens.HourlyBreakdown.name) {
                HourlyBreakdownScreen(
                )
            }

            composable(route = AppScreens.Settings.name) {
                SettingsScreen(
                    selectedOption = savedLanguage,
                    onOptionSelected = { },
                    settingsViewModel = SettingsViewModel(),
                    handleClickBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}