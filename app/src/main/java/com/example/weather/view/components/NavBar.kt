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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.R


@Preview(showBackground = true)
@Composable
fun NavBar(
    modifier: Modifier = Modifier,
    selectedItem: Int? = null,
    onDailyClicked: () -> Unit = {},
    onSelectorClicked: () -> Unit = {},
    onHourlyClicked: () -> Unit = {},
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.outline_location_city_24), contentDescription = "Location City") },
            selected = selectedItem == 0,
            onClick = {
                onSelectorClicked()
            }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.hourly), contentDescription = "hourly") },
            selected = selectedItem == 1,
            onClick = {
                onHourlyClicked()
            }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.daily), contentDescription = "daily") },
            selected = selectedItem == 2,
            onClick = {
                onDailyClicked()
            }
        )
    }
}
