package com.example.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Preview(showBackground = true)
@Composable
fun TextInputBox(
    modifier: Modifier = Modifier.fillMaxSize(),
    onDismissRequest: ()-> Unit = {},
    onConfirmRequest: ()-> Unit = {}
){
    var textInput by remember { mutableStateOf("") }
    Surface(
        modifier = modifier
    ) {
        Dialog(
            onDismissRequest = onDismissRequest
        ) {
            Card(modifier = modifier) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    Text(text = "Name", modifier = Modifier.padding(16.dp))

                    OutlinedTextField(
                        value = textInput,
                        onValueChange = { textInput = it },
                        label = { Text("Input city name") }
                    )
                    Row (
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        TextButton(
                            onClick = { onDismissRequest()}
                        ) {
                            Text(text = "Cancel")
                        }
                        TextButton(
                            onClick = { onConfirmRequest() }
                        ){
                            Text(text = "OK")
                        }
                    }
                }
            }
        }
    }
}