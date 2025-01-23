package com.example.weather.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

// Missing some datapoints to be added later notice that timestamps are stored as UNIX Time / epoch time

@Entity(
    primaryKeys = ["timestamp", "location"],
    foreignKeys = [
        ForeignKey(
            entity = LocationData::class,
            parentColumns = ["id"],
            childColumns = ["location"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class HourData(
    @ColumnInfo var timestamp: Int,
    @ColumnInfo var location: Long,
    @ColumnInfo var updatedAt: Int ,

    @ColumnInfo var humidity: Double,
    @ColumnInfo var temperature: Double,
    @ColumnInfo var windSpeed: Double,
    @ColumnInfo var uv: Double,
    @ColumnInfo var condition: String,
)
