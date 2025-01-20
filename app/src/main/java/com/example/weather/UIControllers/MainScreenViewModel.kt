package com.example.weather.UIControllers

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.Repository.WeatherRepository
import com.example.weather.model.Condition
import com.example.weather.model.CurrentData
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import com.example.weather.model.LocationData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
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

            launch {
                WeatherRepository.getDays(context, 25.67594, 11.56553, "Copenhagen")
                    .distinctUntilChanged()
                    .collect {
                    _dayDataState.value = it
                }
                Log.d("MainScreenViewModel", "Day data updated: ${dayDataState.value.toString()}")
            }

            launch {
                WeatherRepository.getHours(context, 25.67594, 11.56553, "Copenhagen")
                    .distinctUntilChanged()
                    .collect {
                    _hourDataState.value = it
                }
                Log.d("MainScreenViewModel", "Hour data updated: ${hourDataState.value.toString()}")
            }
        }
    }




}
