package com.example.weather.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrentData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var timestamp: Int,
    var locationID: Long,
    var updatedAt: Int,
    var temperature: Double,
    var feelsLike: Double,
    var humidity: Double,
    var uvi: Double,
    var windSpeed: Double,
    var condition: String

)
