package com.example.weather.view.popup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.Repository.CityGeoLocatorData
import com.example.weather.UIControllers.CitySelectorViewModel
import com.example.weather.model.LocationData
import java.time.Instant
import com.example.weather.R

@Composable
fun CitySelectorPopup(
    onDismissRequest: () -> Unit,
    onAddCity: (String) -> Unit
) {
var cityName = ""
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Add a City",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )

                TextField(
                    value = cityName,
                    onValueChange = { cityName = it },
                    placeholder = { Text("Enter city name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        onSearchCity(cityName)
                        //onAddCity(cityName)
                        //onDismissRequest()
                    }) {
                        Text("Search")
                    }

                }

                Log.d("CitySelectorPopup", "Items: ${items.toString()}")
                LazyColumn {
                    items(items, key = null) { item ->
                        Log.d("CitySelectorPopup", "Item: $item")
                        Card (
                            onClick = {
                                onAddCity(
                                    LocationData(
                                        name = item.name ?: "Name Missing",
                                        latitude = item.lat ?: 0.0,
                                        longitude = item.lon ?: 0.0,
                                        updatedAt = Instant.now().epochSecond.toInt(),
                                        isFavourite = false,
                                        id = 0 // placeholder as not transmitted to database
                                        )
                                )
                                onDismissRequest()
                                      },
                        ){
                            Text(text = item.name ?: "Something went wrong")
                            Text(text = item.country ?: "Something went wrong")
                            Text(text = item.state ?: "No State to show")
                        }
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun CitySelectorPopupPreview() {
    CitySelectorPopup(
        onDismissRequest = {},
        onSearchCity = { "" },
        onAddCity = { city -> println("Added city: $city") },
        viewModel = CitySelectorViewModel(LocalContext.current)
    )
}
