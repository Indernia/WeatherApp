package com.example.weather.UIControllers

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.Repository.WeatherRepository
import com.example.weather.model.Condition
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import com.example.weather.model.LocationData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.time.ZonedDateTime
import kotlin.contracts.contract

class MainScreenViewModel: ViewModel() {
    // Store weather data as StateFlow for UI observation
    suspend fun getWeatherData(context: Context) {
        val weatherRepository = WeatherRepository()

        weatherRepository.UpdataData("Test", context = context)
    }


}