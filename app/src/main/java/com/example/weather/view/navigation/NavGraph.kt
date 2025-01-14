package com.example.weather.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.DailyBreakdownScreen
import kotlinx.serialization.Serializable
import com.example.weather.view.screens.*

@Serializable
object Main
@Serializable
object HourlyBreakdown
@Serializable
object DailyBreakdown
@Serializable
object Settings
@Serializable
object CitySelector

@Composable
fun Navgraph(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Main){
        composable<Main> { MainScreen() }
        composable<DailyBreakdown> { DailyBreakdownScreen() }
        composable<HourlyBreakdown> { HourlyBreakdownScreen() }
        composable<CitySelector> { CitySelectorScreen() }
    }
}