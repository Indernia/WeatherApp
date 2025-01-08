package com.example.weather.view.components


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun BackArrowButton (modifier: Modifier = Modifier,
                     handleClickBack: () -> Unit = {}){ // Ved ikke om handleClickBack stadigvæk virker er taget fra hourforecast

            IconButton(
                onClick = { handleClickBack() },
                modifier = modifier.then(Modifier.size(48.dp))
            )
            {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "BackArrow",
                    modifier = Modifier.size(24.dp)
                )
            }

}