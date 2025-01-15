package com.example.weather.view.screens


import android.util.Log
import com.example.weather.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.UIControllers.MainScreenViewModel
import com.example.weather.view.components.FigureComponent
import com.example.weather.view.components.HourSlider
import com.example.weather.view.components.DaySlider
import com.example.weather.view.components.MainScreenInfoComponent
import com.example.weather.view.components.NavBar
import kotlinx.coroutines.launch
import java.util.stream.IntStream.range
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.model.DayData
import com.example.weather.model.HourData
import com.example.weather.view.components.HourDropDown
import com.example.weather.view.components.HourDropDownPreview

@Preview(showBackground = true)
@Composable
fun MainScreen (
    temperature: String = "10",
    city: String = "Copenhagen",
    onMainInfoClicked: () -> Unit = {},
    onSettingsClicked: () -> Unit = {},
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
){
    val context = LocalContext.current
    val mainViewModel = remember { MainScreenViewModel(context = context) }
    val dayDataList by mainViewModel.dayDataState.collectAsState()
    val hourDataList by mainViewModel.hourDataState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "Background Image",
        contentScale = ContentScale.Crop, // Scale the image to fill the screen
        modifier = Modifier.fillMaxSize()
    )
    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier =  Modifier.fillMaxSize()
        ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { onSettingsClicked() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.settings_24px),
                    contentDescription = "Daily Breakdown"
                )
            }
        }
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .height(490.dp)

            ) {
                MainScreenInfoComponent(
                    city = city,
                    temp = temperature,
                    R.drawable.hat,
                    R.drawable.trunks,
                    onClick = { /* ToDO */ }
                )
            }
            HourSlider(
                data = hourDataList,
                modifier = Modifier.height(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            DaySlider(
                data = dayDataList,
                modifier = Modifier.height(100.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
}   }