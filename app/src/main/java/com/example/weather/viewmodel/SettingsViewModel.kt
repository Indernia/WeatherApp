package com.example.weather.viewmodel

import android.content.Context
import android.content.SharedPreferences
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
}