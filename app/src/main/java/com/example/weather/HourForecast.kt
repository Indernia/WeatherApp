package com.example.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun HourlyForecast (
    modifier: Modifier = Modifier.fillMaxSize(),
    handleClickBack: () -> Unit = {}
){
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround

    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = modifier.weight(5f)
        ){
            IconButton(onClick = {handleClickBack()}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "BackArrow"
                )
            }
        }
        Box(
            modifier = modifier.background(Color.LightGray).weight(10f),
            contentAlignment = Alignment.Center
        ){
            Text(text = "Date and Time")
        }
        Spacer(modifier=modifier.weight(2f))


        val info = arrayOf("Hour 1", "Hour 2", "Hour 3", "Hour 4", "Hour 5", "Hour &")
        for(x in info){
            InfoBox(modifier = modifier.weight(10f), info = x)
            Spacer(modifier = modifier.weight(2f))
        }
    }

}

@Composable
private fun InfoBox(
    modifier: Modifier = Modifier,
    info: String = ""
){
    Box(
        modifier = modifier
            .background(Color.LightGray)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            text = "$info"
        )
    }
}