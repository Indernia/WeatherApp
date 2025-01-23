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
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val baseFigureRes = when (weatherCondition) {
        "Clear" -> R.drawable.standard
        "Rain", "Drizzle", "Thunderstorm" -> R.drawable.rain
        "Snow" -> R.drawable.winter
        else -> R.drawable.winter
    }

    /*val clothingRes = when (weatherCondition) {
        "Clear" -> R.drawable.trunks
        "Rainy" -> R.drawable.rainboots
        "Snowy" -> R.drawable.winterboots
        else -> R.drawable.trunks
    }

    val accessoryRes = when (weatherCondition) {
        "Clear" -> R.drawable.hat
        "Rainy" -> R.drawable.umbrella
        "Snowy" -> R.drawable.winterhat
        else -> R.drawable.hat
    }

    val clothingSize = when (weatherCondition) {
        "Clear" -> Pair(125, 102)
        "Rainy" -> Pair(150, 100)
        "Snowy" -> Pair(150, 100)
        else -> Pair(200, 150)
    }

    val accessorySize = when (weatherCondition) {
        "Clear" -> Pair(100, 100)
        "Rainy" -> Pair(240, 200)
        "Snowy" -> Pair(100, 100)
        else -> Pair(100, 80)
    }

    val clothingAlignment = when (weatherCondition) {
        "Clear" -> Alignment.BottomCenter
        "Rainy" -> Alignment.BottomCenter
        "Snowy" -> Alignment.BottomCenter
        else -> Alignment.BottomCenter
    }

    val accessoryAlignment = when (weatherCondition) {
        "Clear" -> Alignment.TopCenter
        "Rainy" -> Alignment.TopCenter
        "Snowy" -> Alignment.TopCenter
        else -> Alignment.TopCenter
    }

    val clothingPadding = when (weatherCondition) {
        "Clear" -> PaddingValues(start = 0.dp, bottom = 23.dp)
        "Rainy" -> PaddingValues(start = 0.dp, bottom = 0.dp)
        "Snowy" -> PaddingValues(start = 4.dp, bottom = 0.dp)
        else -> PaddingValues(0.dp)
    }

    val accessoryPadding = when (weatherCondition) {
        "Clear" -> PaddingValues(top = 5.dp, start = 3.dp)
        "Rainy" -> PaddingValues(top = 200.dp, end = 200.dp)
        "Snowy" -> PaddingValues(top = 0.dp, start = 5.dp)
        else -> PaddingValues(0.dp)
    }
*/
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        val widthRatio = maxWidth / 300 // Reference width of 360
        val heightRatio = maxHeight / 500 // Reference height of 800

        //val widthRatio = maxWidth / 360 // Reference width of 360
        //val heightRatio = maxHeight / 500

        Box(
            modifier = Modifier
                .width((300 * widthRatio.value).dp)
                .height((500 * heightRatio.value).dp)
              //  .width(300.dp)
              //  .height(500.dp)
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
                    /*clothingResId = clothingRes,
                    accessoryResId = accessoryRes,
                    clothingAlignment = clothingAlignment,
                    clothingPadding = clothingPadding,
                    accessoryAlignment = accessoryAlignment,
                    accessoryPadding = accessoryPadding,
                    clothingSize = clothingSize,
                    accessorySize = accessorySize,
                    */
                    modifier = Modifier
                        .height((390 * heightRatio.value).dp))
            }
        }
    }
}
/*
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewMainScreenInfoComponent() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .background(Color(0xFF070E0B))
                .height(650.dp)
                .padding(top = 100.dp)
                .wrapContentSize(Alignment.TopCenter)
        ) {
            MainScreenInfoComponent(
                city = "Copenhagen",
                temp = "10",
                weatherCondition = "Rainy",
                onClick = { /* ToDO */ }
            )
        }
    }
}

 */

