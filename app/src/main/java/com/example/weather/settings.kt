package com.example.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun settings(
    modifier: Modifier = Modifier.fillMaxSize(),
    handleClickBack: () -> Unit = {}
){
    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ){
        Row(
            horizontalArrangement = Arrangement.Start
        ){
            IconButton(onClick = {handleClickBack()}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "BackArrow"
                )
            }
        }
        Text(text = "Settings")
    }

}