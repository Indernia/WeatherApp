package com.example.weather.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weather.model.LocationData

@Composable
fun LocationRepresentationCard(
    modifier: Modifier = Modifier,
    location: LocationData,
    onClick: () -> Unit = {}
){
    OutlinedCard(
        modifier = modifier,
        onClick = onClick
    ) {
        Row {
            Text(
                text = location.name
            )
        }
    }
}