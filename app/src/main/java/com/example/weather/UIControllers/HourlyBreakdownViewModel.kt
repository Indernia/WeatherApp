package com.example.weather.UIControllers

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.Repository.WeatherRepository
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HourlyBreakdownViewModel(context: Context): ViewModel() {

    private val _hourDataState = MutableStateFlow<List<HourData>>(emptyList())
    val hourDataState: StateFlow<List<HourData>> = _hourDataState

    init {
        viewModelScope.launch {

            WeatherRepository().getHours(context, 55.67594, 12.56553, "Copenhagen")
                    .distinctUntilChanged()
                    .collect {
                        _hourDataState.value = it
                    }
        }
    }
}