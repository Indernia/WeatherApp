package com.example.weather.view.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun FigureComponent(
    @DrawableRes baseFigureResId: Int,
    @DrawableRes clothingResId: Int,
    @DrawableRes accessoryResId: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = baseFigureResId),
            contentDescription = "Base Figure",
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )

        Image(
            painter = painterResource(id = clothingResId),
            contentDescription = "Clothing",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 0.dp, bottom = 24.dp),
        )

        Image(
            painter = painterResource(id = accessoryResId),
            contentDescription = "Accessory",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 0.dp, start = 0.dp),
        )
    }
}