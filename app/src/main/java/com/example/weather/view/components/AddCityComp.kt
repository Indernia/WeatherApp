package com.example.weather.view.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.R
import com.example.weather.Repository.CityGeoLocatorData
import com.example.weather.Repository.GeoLocationRepository
import com.example.weather.Repository.WeatherRepository
import com.example.weather.UIControllers.CitySelectorViewModel
import com.example.weather.view.popup.CitySelectorPopup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import kotlin.math.log

@Composable
fun AddCityComp(
    modifier: Modifier = Modifier,
    citySelectorViewModel: CitySelectorViewModel
) {
    // popup visibility
    val showDialog = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    var response: ResponseBody = ResponseBody.create(null, "")

    // the list of cities should be stored elsewhere
    val cities = remember { mutableStateListOf<String>() }

    OutlinedCard(
        onClick = { showDialog.value = true },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .size(width = 350.dp, height = 130.dp)
            .padding(16.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "Add City",
                modifier = Modifier.size(30.dp)
            )
        }    }
    val context = LocalContext.current
    // Show CitySelectorPopup when dialog is visible
    if (showDialog.value) {
        CitySelectorPopup(
            onDismissRequest = { showDialog.value = false },
            onSearchCity = {
                CoroutineScope(Dispatchers.IO).launch{
                    val asyncData: ResponseBody = citySelectorViewModel.getSuggestedLocations(it, context)
                    response = asyncData
                    val locations: List<CityGeoLocatorData> = GeoLocationRepository().convertResponseBodyToGeoLocationData(response)
                    citySelectorViewModel.possibleLocations.value = locations
                    Log.d("CitySelectorViewModel", "City name: ${citySelectorViewModel.possibleLocations.value.toString()}")
                }
            },
            onAddCity = {
                val repo = WeatherRepository()
                CoroutineScope(Dispatchers.IO).launch {
                    repo.UpdataData(
                        name = it.name,
                        lat = it.latitude,
                        lon = it.longitude,
                        context = context
                    )
                }
            },
            viewModel = citySelectorViewModel
        )
    }
}
suspend fun fetchLocationData(cityName: String, context: Context, viewModel: CitySelectorViewModel): List<CityGeoLocatorData> {
    val responseBody = viewModel.getSuggestedLocations(cityName, context)
    return GeoLocationRepository().convertResponseBodyToGeoLocationData(responseBody)
}

@Preview
@Composable
fun AddCityPreview() {
    AddCityComp(
        citySelectorViewModel = CitySelectorViewModel(LocalContext.current)
    )
}
