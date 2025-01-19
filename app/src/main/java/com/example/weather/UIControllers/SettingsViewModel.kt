package com.example.weather.UIControllers

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel: ViewModel()  {
    private val _currentLanguage = MutableStateFlow("da")
    val currentLanguage: StateFlow<String> get() = _currentLanguage

    fun changeLanguage(language: String) {
        _currentLanguage.value = language
    }
}