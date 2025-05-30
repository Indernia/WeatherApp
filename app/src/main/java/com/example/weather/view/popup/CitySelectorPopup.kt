package com.example.weather.view.popup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.viewmodel.CitySelectorViewModel
import com.example.weather.data.LocationData
import java.time.Instant
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.res.stringResource
import com.example.weather.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitySelectorPopup(
    onDismissRequest: () -> Unit,
    onAddCity: (LocationData) -> Unit,
    onSearchCity: (String) -> Unit,
    viewModel: CitySelectorViewModel
) {
var cityName by remember {mutableStateOf("")}
val items by viewModel.possibleLocations.collectAsState()
    Dialog(
        onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.AddCity) ,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )

                TextField(
                    value = cityName,
                    onValueChange = { cityName = it },
                    placeholder = { Text(stringResource(R.string.InsertCity)) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.surface // Set TextField background color
                    )
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(stringResource(R.string.Cancel))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        onSearchCity(cityName)
                    }) {
                        Text(stringResource(R.string.Search))
                    }

                }

                Log.d("CitySelectorPopup", "Items: ${items.toString()}")
                LazyColumn {
                    items(items, key = null) { item ->
                        Log.d("CitySelectorPopup", "Item: $item")
                        Card (
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            modifier = Modifier.fillMaxWidth(),
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
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

            }
        }
    }
}
