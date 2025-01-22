package com.example.weather.view.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.weather.UIControllers.CitySelectorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CitySelectionContainer(
    modifier: Modifier = Modifier,
    viewModel: CitySelectorViewModel,
    navController: NavHostController
    ) {
    val data = viewModel.LocationDataState.collectAsState()
    val context = LocalContext.current
    val composableScope = rememberCoroutineScope()
    Column {

        LazyColumn {
            items(data.value.sortedByDescending { it.isFavourite }) { item ->
                LocationRepresentationCard(
                    modifier = modifier,
                    location = item,
                    onClick = {
                        viewModel.updateCurrentLocation(item.id, context = context)
                        Log.d("CitySelectionContainer", "Current location updated to ${item.id}")
                    },
                    onFavouriteClick = {
                        viewModel.toggleFavourite(item.id, context = context)
                    },
                    onDeleteClick = {
                        composableScope.launch {
                            viewModel.deleteLocation(item.id, context = context)
                        }
                        navController.navigateUp()
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