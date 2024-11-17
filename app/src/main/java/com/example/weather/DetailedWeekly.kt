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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun DetailedWeekly (
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
            modifier = modifier.weight(1.5f)
        ){
            IconButton(onClick = {handleClickBack()}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "BackArrow"
                )
            }
        }
        Text(
            text = "Average temperature forecast" ,
            style = TextStyle(fontSize = 20.sp)
        )
        Box(
            modifier = modifier.padding(12.dp).background(Color.LightGray).weight(4.5f),
        )
        Spacer(modifier=modifier.weight(1f))

        Text(
            text = "8-13 forecast",
            style = TextStyle(fontSize = 20.sp)
        )

        Spacer(modifier=modifier.weight(0.5f))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.padding(15.dp).weight(2.5f)
        ){
            for(num in 1..5){
                Box(modifier = modifier.weight(4f).background(Color.LightGray))
                if(num != 5) {
                    Spacer(modifier = modifier.weight(2f))
                }
        }
        }
        Spacer(modifier=modifier.weight(8f))
    }
}
