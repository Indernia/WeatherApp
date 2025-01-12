package com.example.weather.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationData(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var name: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var updatedAt: Int = 0,
)
