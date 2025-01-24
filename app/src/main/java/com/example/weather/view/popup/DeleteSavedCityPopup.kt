package com.example.weather.view.popup

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.weather.R

@Composable
fun DeleteSavedCityPopup(
    onDismissRequest: () -> Unit,
    city: String,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(stringResource(R.string.DeleteCity))
        },
        text = {
            Text(text = "${stringResource(R.string.Youaredeleting)} $city")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(stringResource(R.string.Delete))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(stringResource(R.string.Cancel))
            }
        }
    )
}