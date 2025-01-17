package com.example.weather.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather.UIControllers.CitySelectorViewModel
import com.example.weather.model.LocationData
import com.example.weather.view.components.LocationRepresentationCard

@Composable
fun CitySelectionContainer(
    modifier: Modifier = Modifier,
    viewModel: CitySelectorViewModel
    ){
    val data = viewModel.LocationDataState.collectAsState()
    Column {

        LazyColumn {
            items(data.value) { item ->
                LocationRepresentationCard(
                    modifier = modifier,
                    location = item,
                    onClick = {

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