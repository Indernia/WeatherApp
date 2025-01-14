package com.example.weather.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface HourDAO {
    @Query("SELECT * FROM HourData")
    fun getAll(): List<HourData>

    @Query("""
        SELECT HourData.*
        FROM HourData 
        LEFT JOIN LocationData on HourData.location = LocationData.id
        WHERE LocationData.latitude = :lat AND LocationData.longitude = :lon
        ORDER BY HourData.timestamp DESC
        LIMIT :limit
    """)
    fun getAllFromLatLon(lat: Double, lon: Double, limit: Int = 24) : Flow<List<HourData>>

    @Query("""
        SELECT HourData.updatedAt
        FROM HourData
        LEFT JOIN LocationData ON HourData.location = LocationData.id
        WHERE LocationData.latitude = :lat AND LocationData.longitude = :lon
        ORDER BY HourData.updatedAt DESC
        LIMIT 1
    """)
    fun getLatestUpdate(lat: Double, lon: Double): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hours: List<HourData>)



}