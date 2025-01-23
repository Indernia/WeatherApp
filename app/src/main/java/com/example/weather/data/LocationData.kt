package com.example.weather.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    indices = [androidx.room.Index(value = ["latitude", "longitude"], unique = true)]
)
data class LocationData(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var name: String = "",
    @ColumnInfo() var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var updatedAt: Int = 0,
    var isFavourite: Boolean = false,
    var isDeleted: Boolean = false,
)
