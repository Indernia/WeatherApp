package com.example.weather.view.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
    onMainClicked: () -> Unit = {},
    onHourlyClicked: () -> Unit = {},
) {
    NavigationBar(
        modifier = modifier
            .consumeWindowInsets(WindowInsets.systemBars)
    ) {

        NavigationBarItem(
            modifier = Modifier
                .padding(WindowInsets.navigationBars.only(WindowInsetsSides.Bottom).asPaddingValues()),
            icon = { Icon(painterResource(id = R.drawable.outline_location_city_24), contentDescription = "Location City") },
            selected = selectedItem == 0,
            onClick = {
                onMainClicked()
            }
        )
        NavigationBarItem(
            modifier = Modifier
                .padding(WindowInsets.navigationBars.only(WindowInsetsSides.Bottom).asPaddingValues()),
            icon = { Icon(painterResource(id = R.drawable.hourly), contentDescription = "hourly") },
            selected = selectedItem == 1,
            onClick = {
                onHourlyClicked()
            }
        )
        NavigationBarItem(
            modifier = Modifier
                .padding(WindowInsets.navigationBars.only(WindowInsetsSides.Bottom).asPaddingValues()),
            icon = { Icon(painterResource(id = R.drawable.daily), contentDescription = "daily") },
            selected = selectedItem == 2,
            onClick = {
                onDailyClicked()
            }
        )
    }
}
