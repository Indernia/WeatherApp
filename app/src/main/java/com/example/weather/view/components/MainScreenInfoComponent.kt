package com.example.weather.view.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.data.DayData
import kotlin.math.roundToInt


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MainScreenInfoComponent(
    city: String,
    temp: String,
    data: DayData?,
    weatherCondition: String,
    feelsLike: Double,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val baseFigureRes = when {
        feelsLike < 10 -> R.drawable.winter
        weatherCondition == "Clear" -> R.drawable.standard
        weatherCondition in listOf("Rain", "Drizzle", "Thunderstorm") -> R.drawable.rain
        weatherCondition == "Snow" -> R.drawable.winter
        else -> R.drawable.winter
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        val widthRatio = maxWidth / 300 // Reference width of 360
        val heightRatio = maxHeight / 500 // Reference height of 800

        Box(
            modifier = Modifier
                .width((300 * widthRatio.value).dp)
                .height((500 * heightRatio.value).dp)
                .background(Color.Transparent),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = city,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = Center
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$temp°C",
                    style = MaterialTheme.typography.titleLarge

                )
                val mincelsiusTemperature = (data?.minTempK?.minus(273.15))?.roundToInt()
                val maxcelsiusTemperature = (data?.maxTempK?.minus(273.15))?.roundToInt()

                if (data != null) {
                    Text(
                        text = "Min: $mincelsiusTemperature° Max: $maxcelsiusTemperature°",
                        style = MaterialTheme.typography.titleSmall

                    )
                }
                Spacer(modifier = Modifier.height(3.dp))

                FigureComponent(
                    baseFigureResId = baseFigureRes,
                    modifier = Modifier
                        .height((390 * heightRatio.value).dp))
            }
        }
    }
}
