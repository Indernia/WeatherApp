package com.example.weather.view.popup

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DeleteSavedCityPopup(
    onDismissRequest: () -> Unit,
    city: String,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = "Delete City?")
        },
        text = {
            Text(text = "You are deleting $city")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}