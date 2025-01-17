package com.example.weather.UIControllers

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel

class NavViewModel : ViewModel() {

    // Track the selected item in the navigation bar (State instead of LiveData)
    private val _selectedItem = mutableStateOf<Int?>(null)
    val selectedItem: State<Int?> = _selectedItem // Expose the state as a read-only property

    // Handle the change in selection (e.g., from clicking a navigation button)
    fun onNavItemSelected(itemId: Int) {
        _selectedItem.value = itemId
    }

    // Reset selected item
    fun resetSelectedItem() {
        _selectedItem.value = null
    }
}
