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
    clothingAlignment: Alignment = Alignment.BottomCenter,
    clothingPadding: PaddingValues = PaddingValues(0.dp),
    accessoryAlignment: Alignment = Alignment.TopCenter,
    accessoryPadding: PaddingValues = PaddingValues(0.dp),
    clothingSize: Pair<Int, Int>? = null,
    accessorySize: Pair<Int, Int>? = null,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = baseFigureResId),
            contentDescription = "Base Figure",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        )

        Image(
            painter = painterResource(id = clothingResId),
            contentDescription = "Clothing",
            modifier = Modifier
                .align(clothingAlignment)
                .padding(clothingPadding)
                .let { baseModifier ->
                    clothingSize?.let { (width, height) ->
                        baseModifier.size(width.dp, height.dp)
                    } ?: baseModifier
                }
        )

        Image(
            painter = painterResource(id = accessoryResId),
            contentDescription = "Accessory",
            modifier = Modifier
                .align(accessoryAlignment)
                .padding(accessoryPadding)
                .let { baseModifier ->
                    accessorySize?.let { (width, height) ->
                        baseModifier.size(width.dp, height.dp)
                    } ?: baseModifier
                }
        )
    }
}