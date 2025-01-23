package com.example.weather.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.R
import com.example.weather.domain.CityGeoLocatorData
import com.example.weather.domain.GeoLocationAPI
import com.example.weather.domain.WeatherRepository
import com.example.weather.data.LocationData
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

            launch {
                WeatherRepository().getLocations(context)
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
        viewModelScope.launch {
            WeatherRepository().setCurrentLocation(id, context)
            Log.d("CitySelectorViewModel", "Current location updated to $id")
        }
    }

    suspend fun deleteLocation(id: Long, context: Context) {
        val weatherRepository = WeatherRepository()
        withContext(Dispatchers.IO) {
            weatherRepository.deleteLocation(id, context)
        }
    }

    fun toggleFavourite(id: Long, context: Context) {
        val weatherRepository = WeatherRepository()
        viewModelScope.launch{
            weatherRepository.toggleLocationFavourite(id = id, context = context)
        }
    }

}