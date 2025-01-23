package com.example.weather.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// This does not really make sense to include in a relational model, but as it would be more trouble to implement a datastore than just add another table going with this approach, for now
@Entity
data class Settings(
    @PrimaryKey var id: Long = 1,
    var currentLocationID: Int = 1, // Use the id of the location in the locationdatabase
    var showFahrenheit: Boolean = false,
    var showCelsius: Boolean = true,
    var temperatureToConsiderWarm: Double = 20.0,
    var temperatureToConsiderCold: Double = 10.0,
    var temperatureToConsiderHot: Double = 30.0, // Please add more for when proper, I just could not think of more
)
