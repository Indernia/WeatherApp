package com.example.weather.view.components

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.weather.R
import com.example.weather.view.components.FigureComponent


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MainScreenInfoComponent(
    city: String,
    temp: String,
    clothingRes: Int,
    accessoryRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier // Add this parameter
) {
    val context = LocalContext.current

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
                .clickable { onClick() }
                .background(Color.Transparent),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = city,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, fontSize = 35.sp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$temp°C",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold, fontSize = 30.sp)

                )
                Spacer(modifier = Modifier.height(3.dp))

                FigureComponent(
                    R.drawable.dude,
                    accessoryRes,
                    clothingRes,
                    modifier = Modifier.height((390 * heightRatio.value).dp))
            }
        }
    }
}

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
                R.drawable.hat,
                R.drawable.trunks,
                onClick = { /* ToDO */ }
            )
        }
    }
}

