package com.example.weather.view.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
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
    onMainClicked: () -> Unit = {},
    onHourlyClicked: () -> Unit = {},
) {
    NavigationBar(
        modifier = modifier
            .padding(WindowInsets.navigationBars.asPaddingValues()),
    ) {

        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.outline_location_city_24), contentDescription = "Location City") },
            selected = selectedItem == 0,
            onClick = {
                onMainClicked()
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
