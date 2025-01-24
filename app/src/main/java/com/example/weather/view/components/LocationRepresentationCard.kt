package com.example.weather.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.data.LocationData

@Composable
fun LocationRepresentationCard(
    modifier: Modifier = Modifier,
    location: LocationData,
    onClick: () -> Unit = {},
    onFavouriteClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(98.dp)
            .padding(horizontal = 16.dp, vertical = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp), // Matches the card height for consistency
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.weight(2f).padding(8.dp),
                text = location.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
            )

            IconButton(
                onClick = onDeleteClick,
                modifier = modifier.weight(0.5f).padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.round_delete_24),
                    contentDescription = "Favourite",
                    tint = Color.Red,
                )
            }

            if( location.isFavourite) {
                IconButton(
                    onClick = onFavouriteClick,
                    modifier = modifier.weight(1f).padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_star_24),
                        contentDescription = "Favourite",
                        tint = Color(0xFFF7B801),
                    )
                }
            } else {
                IconButton(
                    onClick = onFavouriteClick,
                    modifier = modifier.weight(1f).padding(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star_outline_24),
                        contentDescription = "Favourite",
                        tint = Color(0xFFF7B801),
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun LocationRepresentationCardPreview() {
    LocationRepresentationCard(
        location = LocationData(
            name = "Copenhagen",
            latitude = 0.0,
            longitude = 0.0,
            updatedAt = 0,
            isFavourite = true,
            id = 1
        )
    )
}