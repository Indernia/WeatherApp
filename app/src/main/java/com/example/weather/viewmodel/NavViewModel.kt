package com.example.weather.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel

class NavViewModel : ViewModel() {

    // Track the selected item in the navigation bar (State instead of LiveData)
    private val _selectedItem = mutableStateOf<Int>(0)
    val selectedItem: State<Int?> = _selectedItem // Expose the state as a read-only property

    // Handle the change in selection (e.g., from clicking a navigation button)
    fun onNavItemSelected(itemId: Int) {
        _selectedItem.value = itemId
    }
}
