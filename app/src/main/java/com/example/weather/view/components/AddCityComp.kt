package com.example.weather.view.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.domain.CityGeoLocatorData
import com.example.weather.domain.GeoLocationRepository
import com.example.weather.domain.WeatherRepository
import com.example.weather.viewmodel.CitySelectorViewModel
import com.example.weather.view.popup.CitySelectorPopup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

@Composable
fun AddCityComp(
    modifier: Modifier = Modifier,
    citySelectorViewModel: CitySelectorViewModel
) {
    // popup visibility
    val showDialog = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    var response: ResponseBody = ResponseBody.create(null, "")


    Card(
        onClick = { showDialog.value = true },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
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
                CoroutineScope(Dispatchers.IO).launch {
                    WeatherRepository().UpdataData(
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
