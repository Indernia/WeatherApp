package com.example.weather.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.weather.domain.WeatherRepository
import com.example.weather.viewmodel.SettingsViewModel
import com.example.weather.data.AppDatabase
import com.example.weather.data.LocationData
import com.example.weather.data.Settings
import com.example.weather.ui.theme.WeatherTheme
import com.example.weather.view.navigation.AppNavHost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.time.Instant

class MainActivity : ComponentActivity() {
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this
        val savedLanguage = settingsViewModel.getLanguagePreference(context)

        settingsViewModel.setLocale(context, savedLanguage)

        val sharedPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

        // Check if it's the first launch or after data clearance
        val isFirstLaunch = sharedPreferences.getBoolean("is_first_launch", true)

        if (isFirstLaunch) {
            // Run your initialization functions
            initializeDatabase()
            // Update the flag to indicate initialization is done
            sharedPreferences.edit()
                .putBoolean("is_first_launch", false)
                .apply()
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT


        Log.d("MainActivity", "Database initialized")
        setContent {
            WeatherTheme {
                AppNavHost()
            }
        }
    }

    private fun initializeDatabase() {
        // Explicitly initialize and prepopulate the database
        val database = AppDatabase.getDatabase(this)
        runBlocking {
            databasePrepopulate(database, this@MainActivity)
        }
    }

    private suspend fun databasePrepopulate(database: AppDatabase, context: Context) {
        withContext(Dispatchers.IO) {
            database.settingsDao().insertSettings(
                Settings(
                    id = 1,
                    currentLocationID = 1,
                )
            )

            Log.d("MainActivity", "Settings inserted")

            database.locationDao().insert(
                LocationData(
                    name = "Copenhagen",
                    latitude = 55.676098,
                    longitude = 12.568337,
                    updatedAt = Instant.now().epochSecond.toInt(),
                    isFavourite = true,
                    id = 0,
                )
            )
            Log.d("MainActivity", "Location inserted")
            // set default location
            WeatherRepository().setCurrentLocation(1, context)
        }
    }

}