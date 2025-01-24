package com.example.weather.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

class SettingsViewModel: ViewModel()  {
    private val _currentLanguage = MutableStateFlow("da")
    val currentLanguage: StateFlow<String> get() = _currentLanguage

    fun changeLanguage(language: String) {
        _currentLanguage.value = language
    }

    fun setLocale(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    fun saveLanguagePreference(context: Context, language: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("language_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("selected_language", language)
        editor.apply()
    }

    fun getLanguagePreference(context: Context): String {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("language_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("selected_language", "da") ?: "da"
    }

    fun getSliderPosition(context: Context): Float {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("slider_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getFloat("slider_position", 0f)
    }

    fun setSliderPosition(context: Context, position: Float) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("slider_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("slider_position", position)
        editor.apply()
    }

}