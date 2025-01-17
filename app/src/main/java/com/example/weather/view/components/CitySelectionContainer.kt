package com.example.weather.view.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.weather.UIControllers.CitySelectorViewModel

@Composable
fun CitySelectionContainer(
    modifier: Modifier = Modifier,
    viewModel: CitySelectorViewModel
    ) {
    val data = viewModel.LocationDataState.collectAsState()
    val context = LocalContext.current
    Column {

        LazyColumn {
            items(data.value) { item ->
                LocationRepresentationCard(
                    modifier = modifier,
                    location = item,
                    onClick = {
                        viewModel.updateCurrentLocation(item.id, context = context)
                        Log.d("CitySelectionContainer", "Current location updated to ${item.id}")
                    }
                )
            }
        }
        AddCityComp(
            modifier = modifier,
            citySelectorViewModel = viewModel
        )
    }
}