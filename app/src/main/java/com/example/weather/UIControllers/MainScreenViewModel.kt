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
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.time.ZonedDateTime
import kotlin.contracts.contract

class MainScreenViewModel (context: Context): ViewModel() {

    private val _dayDataState = MutableStateFlow<List<DayData>>(emptyList())
    val dayDataState: StateFlow<List<DayData>> = _dayDataState

    private val _hourDataState = MutableStateFlow<List<HourData>>(emptyList())
    val hourDataState: StateFlow<List<HourData>> = _hourDataState

    init {
        viewModelScope.launch {
            val weatherRepository = WeatherRepository()

            launch {
                weatherRepository.getDays(context, 55.67594, 12.56553, "Copenhagen")
                    .distinctUntilChanged()
                    .collect {
                    _dayDataState.value = it
                }
            }

            launch {
                weatherRepository.getHours(context, 55.67594, 12.56553, "Copenhagen")
                    .distinctUntilChanged()
                    .collect {
                    _hourDataState.value = it
                }
            }
        }
    }




}