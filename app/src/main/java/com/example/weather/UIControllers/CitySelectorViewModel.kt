package com.example.weather.UIControllers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.R
import com.example.weather.Repository.CityGeoLocatorData
import com.example.weather.Repository.GeoLocationAPI
import com.example.weather.Repository.WeatherRepository
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import com.example.weather.model.LocationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class CitySelectorViewModel (
    context: Context
): ViewModel(){

    private val _LocationDataState = MutableStateFlow<List<LocationData>>(emptyList())
    val LocationDataState: StateFlow<List<LocationData>> = _LocationDataState

    var possibleLocations: MutableStateFlow<List<CityGeoLocatorData>> = MutableStateFlow(emptyList())


    init {
        viewModelScope.launch {
            val weatherRepository = WeatherRepository()

            launch {
                weatherRepository.getLocations(context)
                    .distinctUntilChanged()
                    .collect {
                        _LocationDataState.value = it
                    }
            }
        }
    }


    suspend fun getSuggestedLocations(
        cityName: String,
        context: Context
    ): ResponseBody {
        var response: ResponseBody
        withContext(Dispatchers.IO) {
            response = GeoLocationAPI.retrofitService.getLocationsData(
                cityName = cityName,
                limit = 10,
                apiKey = context.getString(R.string.api_key)
            )
        }
        return response
    }

    fun updateCurrentLocation(id: Long, context: Context) {
        val weatherRepository = WeatherRepository()
        viewModelScope.launch {
            weatherRepository.setCurrentLocation(id, context)
            Log.d("CitySelectorViewModel", "Current location updated to $id")
        }

    }

}