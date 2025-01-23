package com.example.weather.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

// We are missing a some datapoints here as the main focus is to get it implemented and working before making it perfect

@Entity(
    primaryKeys = ["date", "location"],
    foreignKeys = [
        ForeignKey(
            entity = LocationData::class,
            parentColumns = ["id"],
            childColumns = ["location"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DayData(
    @ColumnInfo var date: String,
    @ColumnInfo var location: Long,
    @ColumnInfo var updatedAt: Int,

    @ColumnInfo var maxTempK: Double? = 0.0,
    @ColumnInfo var minTempK: Double? = 0.0,
    @ColumnInfo var tempK: Double? = 0.0,


    @ColumnInfo var humidity: Double? = 0.0,

    @ColumnInfo var uvi: Double? = 0.0,

    @ColumnInfo var windSpeed: Double = 0.0,
    @ColumnInfo var windGustSpeed: Double = 0.0,

    @ColumnInfo var weatherCondition: String?,
)
