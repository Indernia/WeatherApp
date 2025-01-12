package com.example.weather.view.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.R


@Preview(showBackground = true)
@Composable
fun NavBar() {
    var selectedItem by remember {mutableStateOf(0) }

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.outline_location_city_24), contentDescription = "Location City") },
            //label = { Text("Location") },
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.hourly), contentDescription = "hourly") },
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.daily), contentDescription = "daily") },
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 }
        )

    }
}

