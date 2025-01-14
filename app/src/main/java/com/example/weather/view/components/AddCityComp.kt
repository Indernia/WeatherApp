package com.example.weather.view.components

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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.view.popup.CitySelectorPopup

@Composable
fun AddCityComp(
    modifier: Modifier = Modifier
) {
    // popup visibility
    val showDialog = remember { mutableStateOf(false) }

    // the list of cities should be stored elsewhere
    val cities = remember { mutableStateListOf<String>() }

    OutlinedCard(
        onClick = { showDialog.value = true },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .size(width = 280.dp, height = 80.dp)
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

    // Show CitySelectorPopup when dialog is visible
    if (showDialog.value) {
        CitySelectorPopup(
            onDismissRequest = { showDialog.value = false },
            onAddCity = { cityName ->
                // Add the city to the list
                if (cityName.isNotBlank() && !cities.contains(cityName)) {
                    cities.add(cityName)
                }
            }
        )
    }
}

@Preview
@Composable
fun AddCityPreview() {
    AddCityComp()
}
