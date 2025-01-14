package com.example.weather.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDAO {
    @Query("SELECT * FROM LocationData")
    fun getAll(): Flow<List<LocationData>>

    @Query("SELECT * FROM LocationData WHERE longitude = :lon AND latitude = :lat")
    fun getLocationByLatLon(lat: Double, lon: Double): Flow<LocationData?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(location: LocationData): Long


}