package com.example.weather.view.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.UIControllers.DailyBreakdownViewModel
import com.example.weather.UIControllers.MainScreenViewModel
import com.example.weather.view.screens.*
import com.example.weather.view.components.NavBar
import com.example.weather.UIControllers.NavViewModel
import com.example.weather.UIControllers.SettingsViewModel


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
    viewModel: NavViewModel = viewModel()
) {
    val selectedItem = viewModel.selectedItem.value

    Scaffold(
        modifier = modifier,
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
                onSelectorClicked = {
                    viewModel.onNavItemSelected(0) // Update selected item in ViewModel
                    navController.navigate(AppScreens.CitySelector.name){
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
                    onSettingsClicked = {navController.navigate(AppScreens.Settings.name)}
                )
            }

            composable(route = AppScreens.CitySelector.name) {
                CitySelectorScreen(
                    handleClickBack = {
                        navController.navigateUp()
                        viewModel.resetSelectedItem() // Reset selected item in ViewModel on navigateUp
                    }
                )
            }

            composable(route = AppScreens.DailyBreakdown.name) {
                DailyBreakdownScreen(
                    dailyViewModel = DailyBreakdownViewModel(context = LocalContext.current),
                    handleClickBack = {
                        navController.navigateUp()
                        viewModel.resetSelectedItem() // Reset selected item in ViewModel on navigateUp
                    }
                )
            }

            composable(route = AppScreens.HourlyBreakdown.name) {
                HourlyBreakdownScreen(
                    handleClickBack = {
                        navController.navigateUp()
                        viewModel.resetSelectedItem() // Reset selected item in ViewModel on navigateUp
                    }
                )
            }

            composable(route = AppScreens.Settings.name) {
                val context = LocalContext.current
                val savedLanguage = getLanguagePreference(context)
                SettingsScreen(
                    selectedOption = savedLanguage,
                    onOptionSelected = { },
                    SettingsViewModel = SettingsViewModel(),
                    handleClickBack = {
                        navController.navigateUp()
                        viewModel.resetSelectedItem() // Reset selected item in ViewModel on navigateUp
                    }
                )
            }
        }
    }
}