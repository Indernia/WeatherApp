package com.example.weather.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun CitySelectorComponentButton(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
){
    Surface(
        modifier = modifier
            .size(56.dp)
            .background(Color.White, shape = CircleShape),
        color = Color.Transparent,
        onClick = onClick
    ) {
            icon.invoke()
        }
    }

@Preview
@Composable
fun CitySelectorComponentButtonPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan) // Baggrundsfarve for at se knappens form tydeligt
        ) {
            CitySelectorComponentButton(
                icon = {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
                        )
                },
                onClick = { /* TODO */ }
            )
        }
    }
}