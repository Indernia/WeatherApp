package com.example.weather.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.CurrentData
import com.example.weather.domain.WeatherRepository
import com.example.weather.data.DayData
import com.example.weather.data.HourData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HourlyBreakdownViewModel(context: Context): ViewModel() {

    private val _dayDataState = MutableStateFlow<List<DayData>>(emptyList())
    val dayDataState: StateFlow<List<DayData>> = _dayDataState

    private val _currentDataState = MutableStateFlow<List<CurrentData>>(emptyList())
    val currentDataState: MutableStateFlow<List<CurrentData>> = _currentDataState

    private val _hourDataState = MutableStateFlow<List<HourData>>(emptyList())
    val hourDataState: StateFlow<List<HourData>> = _hourDataState

    val currentCity = mutableStateOf("")

    init {
        viewModelScope.launch {

            launch {
                WeatherRepository().getDays(context, 55.67594, 12.56553, "Copenhagen")
                    .distinctUntilChanged()
                    .collect {
                        _dayDataState.value = it
                    }
            }

            launch {
                WeatherRepository().getHours(context, 55.67594, 12.56553, "Copenhagen")
                    .distinctUntilChanged()
                    .collect {
                        _hourDataState.value = it
                    }
            }

            launch {
                WeatherRepository().getCurrent(context, 25.67594, 11.56553, "Copenhagen")
                    .distinctUntilChanged()
                    .collect {
                        _currentDataState.value = listOf(it)
                    }
            }

            launch {
                currentCity.value = WeatherRepository().getCurrentCity(context)
            }
        }
    }
}