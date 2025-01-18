package com.example.weather.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.weather.ui.theme.WeatherTheme
import com.example.weather.view.navigation.AppNavHost
import com.example.weather.view.screens.setLocale
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLocale(this, "da")
        setContent {
            WeatherTheme {
                AppNavHost()
            }
        }
    }
}
